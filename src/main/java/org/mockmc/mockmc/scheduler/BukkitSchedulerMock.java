package org.mockmc.mockmc.scheduler;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import org.mockmc.mockmc.AsyncCatcher;
import org.mockmc.mockmc.MockMC;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.EntityMock;
import org.mockmc.mockmc.exception.AsyncTaskException;
import org.mockmc.mockmc.exception.TaskCancelledException;
import org.mockmc.mockmc.world.WorldMock;
import org.opentest4j.AssertionFailedError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Mock implementation of a {@link BukkitScheduler}.
 */
public class BukkitSchedulerMock implements org.mockmc.mockmc.generated.org.bukkit.scheduler.BukkitSchedulerBaseMock
{

	private static final String LOGGER_NAME = "BukkitSchedulerMock";

	private final ThreadPoolExecutor pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
			new SynchronousQueue<>());

	private final ExecutorService asyncEventExecutor = Executors.newCachedThreadPool();

	private final List<Future<Void>> queuedAsyncEvents = Collections.synchronizedList(new ArrayList<>());

	private final TaskList scheduledTasks = new TaskList();

	private final List<BukkitWorker> activeWorkers = Collections.synchronizedList(new ArrayList<>());

	private final AtomicReference<Exception> asyncException = new AtomicReference<>();

	// This variable must be accessed while synchronizing on
	// BukkitSchedulerMock.this to avoid data races and race conditions
	private long currentTick = 0;

	private final AtomicInteger id = new AtomicInteger();

	private final AtomicLong executorTimeout = new AtomicLong(60000);

	// Always read-only. The reference is updated by saveOverdueTasks()
	private final AtomicReference<List<BukkitWorker>> overdueTasks = new AtomicReference<>(List.of());

	@NotNull
	private Runnable wrapTask(@NotNull ScheduledTask task)
	{
		return () ->
		{
			task.setRunning(true);
			if (!task.isSync())
			{
				this.activeWorkers.add(task);
			}
			try
			{
				task.run();
			} finally
			{
				if (!task.isSync())
				{
					synchronized (this.activeWorkers)
					{
						this.activeWorkers.remove(task);
						this.activeWorkers.notifyAll();
					}
				}
				task.setRunning(false);
			}
		};
	}

	/**
	 * Sets the maximum time to wait for async tasks to finish before terminating
	 * them.
	 *
	 * @param timeout
	 *            The timeout in milliseconds.
	 */
	public void setShutdownTimeout(long timeout)
	{
		this.executorTimeout.set(timeout);
	}

	/**
	 * Shuts the scheduler down. Note that this function will throw exception that
	 * where thrown by old asynchronous tasks.
	 */
	public void shutdown()
	{
		waitAsyncTasksFinished();
		shutdownPool(pool);
		// Single read from volatile variable
		Exception exception = this.asyncException.get();
		if (exception != null)
		{
			throw new AsyncTaskException(exception);
		}
		waitAsyncEventsFinished();
		shutdownPool(asyncEventExecutor);
	}

	/**
	 * Shuts down the given executor service, waiting up to the shutdown timeout for
	 * all tasks to finish.
	 *
	 * @param pool
	 *            The pool to shut down.
	 * @see #setShutdownTimeout(long)
	 */
	private void shutdownPool(@NotNull ExecutorService pool)
	{
		pool.shutdown();
		try
		{
			if (!pool.awaitTermination(this.executorTimeout.get(), TimeUnit.MILLISECONDS))
			{
				pool.shutdownNow();
			}
		} catch (InterruptedException _)
		{
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Executes an asynchronous event.
	 *
	 * @param event
	 *            The event to execute.
	 * @return A future representing the task.
	 */
	@NotNull
	public Future<Void> executeAsyncEvent(@NotNull Event event)
	{
		return executeAsyncEvent(event, null);
	}

	/**
	 * Executes an asynchronous event.
	 *
	 * @param event
	 *            The event to execute.
	 * @param func
	 *            A consumer to call after the event is invoked.
	 * @param <T>
	 *            The event type.
	 * @return A future representing the task.
	 */
	@NotNull
	public <T extends Event> Future<Void> executeAsyncEvent(@NotNull T event, @Nullable Consumer<T> func)
	{
		MockMC.ensureMocking();
		Preconditions.checkNotNull(event, "Cannot call a null event!");
		Future<Void> future = asyncEventExecutor.submit(() ->
		{
			MockMC.getMock().getPluginManager().callEvent(event);
			if (func != null)
			{
				func.accept(event);
			}
		}, null);
		queuedAsyncEvents.add(future);
		return future;
	}

	/**
	 * Get the current tick of the server.
	 *
	 * @return The current tick of the server.
	 */
	public synchronized long getCurrentTick()
	{
		return currentTick;
	}

	private void processWorlds()
	{
		for (World world : Bukkit.getWorlds())
		{
			((WorldMock) world).tick();
		}
	}

	private void processEntities()
	{
		for (EntityMock entity : ((ServerMock) Bukkit.getServer()).getEntities())
		{
			if (!entity.isValid())
			{
				continue;
			}
			entity.tick();
		}
	}

	private void processTasks()
	{
		List<ScheduledTask> oldTasks = scheduledTasks.getCurrentTaskList();
		for (ScheduledTask task : oldTasks)
		{
			if (task.getScheduledTick() != currentTick || task.isCancelled())
			{
				continue;
			}
			if (task.isSync())
			{
				wrapTask(task).run();
			} else
			{
				task.submitted();
				pool.submit(wrapTask(task));
			}
			if (task instanceof RepeatingTask repeatingTask)
			{
				if (!task.isCancelled())
				{
					repeatingTask.updateScheduledTick();
					scheduledTasks.addTask(task);
				}
			} else
			{
				task.cancel();
			}
		}
	}

	/**
	 * Perform one tick on the server.
	 */
	public void performOneTick()
	{
		AsyncCatcher.catchOp("server tick");
		// Using a synchronized block here is necessary to avoid tasks being skipped by
		// processTasks().
		// runTask*(..) methods will synchronize with this synchronized block and keep
		// the lock from the moment they read
		// currentTick to when they register their task into scheduledTasks. This
		// ensures that the main thread will wait
		// for the async threads to register their tasks before incrementing
		// currentTick. Thus, since the delay of every
		// task is always >= 1, tasks scheduled during a tick can never be skipped
		synchronized (this)
		{
			currentTick++;
		}
		processWorlds();
		processEntities();
		processTasks();
	}

	/**
	 * Perform a number of ticks on the server.
	 *
	 * @param ticks
	 *            The number of ticks to execute.
	 */
	public void performTicks(long ticks)
	{
		for (long i = 0; i < ticks; i++)
		{
			performOneTick();
		}
	}

	/**
	 * Gets the number of async tasks which are awaiting execution.
	 *
	 * @return The number of async tasks which are pending execution.
	 */
	public int getNumberOfQueuedAsyncTasks()
	{
		int queuedAsync = 0;
		for (ScheduledTask task : scheduledTasks.getCurrentTaskList())
		{
			if (task.isSync() || task.isCancelled() || task.isRunning())
			{
				continue;
			}
			queuedAsync++;
		}
		return queuedAsync;
	}

	/**
	 * Waits until all asynchronous tasks have finished executing. If you have an
	 * asynchronous task that runs indefinitely, this function will never return.
	 * Note that this will not wait for async events to finish.
	 */
	public void waitAsyncTasksFinished()
	{
		// Cancel repeating tasks so they don't run forever.
		scheduledTasks.cancelRepeatingTasks();
		// Make sure all tasks get to execute. (except for repeating asynchronous tasks,
		// they only will fire once)
		while (scheduledTasks.getScheduledTaskCount() > 0)
		{
			performOneTick();
		}
		// Wait for all tasks to finish executing.
		long systemTime = System.currentTimeMillis();
		while (pool.getActiveCount() > 0)
		{
			try
			{
				Thread.sleep(10L);
			} catch (InterruptedException _)
			{
				Thread.currentThread().interrupt();
				return;
			}
			if (System.currentTimeMillis() <= (systemTime + executorTimeout.get()))
			{
				continue;
			}
			// If a plugin has left a runnable going and not cancelled it we could call this
			// bad practice.
			// We should force interrupt all these runnables, forcing them to throw
			// Interrupted Exceptions
			// if they handle that.
			for (ScheduledTask task : scheduledTasks.getCurrentTaskList())
			{
				if (!task.isRunning())
				{
					continue;
				}
				task.cancel();
				throw new TaskCancelledException("Forced Cancellation of task owned by " + task.getOwner().getName());
			}
			pool.shutdownNow();
		}
	}

	/**
	 * Blocks until all asynchronous event invocations are finished.
	 */
	public void waitAsyncEventsFinished()
	{
		List<Future<Void>> queuedAsyncEventsCopy;
		synchronized (queuedAsyncEvents)
		{
			queuedAsyncEventsCopy = List.copyOf(queuedAsyncEvents);
		}
		for (Future<Void> futureEvent : queuedAsyncEventsCopy)
		{
			if (futureEvent.isDone())
			{
				queuedAsyncEvents.remove(futureEvent);
			} else
			{
				try
				{
					queuedAsyncEvents.remove(futureEvent);
					futureEvent.get();
				} catch (InterruptedException _)
				{
					Thread.currentThread().interrupt();
				} catch (ExecutionException e)
				{
					throw new AsyncTaskException(e);
				}
			}
		}
	}

	@Override
	@NotNull
	public BukkitTask runTask(@NotNull Plugin plugin, @NotNull Runnable task)
	{
		return runTaskLater(plugin, task, 1L);
	}

	/**
	 * @deprecated Use {@link #runTask(Plugin, Runnable)} instead.
	 */
	@Override
	@Deprecated(since = "1.7.10")
	@NotNull
	public BukkitTask runTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task)
	{
		return runTask(plugin, (Runnable) task);
	}

	@Override
	@NotNull
	public BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull Runnable task, long delay)
	{
		delay = Math.max(delay, 1);
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			ScheduledTask scheduledTask = new ScheduledTask(id.getAndIncrement(), plugin, true, currentTick + delay,
					task);
			scheduledTasks.addTask(scheduledTask);
			return scheduledTask;
		}
	}

	@Override
	@NotNull
	public BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period)
	{
		delay = Math.max(delay, 1);
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			RepeatingTask repeatingTask = new RepeatingTask(id.getAndIncrement(), plugin, true, currentTick + delay,
					period, task);
			scheduledTasks.addTask(repeatingTask);
			return repeatingTask;
		}
	}

	/**
	 * @deprecated Use {@link #runTaskTimer(Plugin, Runnable, long, long)} instead.
	 */
	@Override
	@Deprecated(since = "1.7.10")
	@NotNull
	public BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay, long period)
	{
		return runTaskTimer(plugin, (Runnable) task, delay, period);
	}

	@Override
	public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay)
	{
		Logger.getLogger(LOGGER_NAME).warning("Consider using runTaskLater instead of scheduleSyncDelayTask");
		return runTaskLater(plugin, task, delay).getTaskId();
	}

	/**
	 * @deprecated Use {@link #runTaskLater(Plugin, Runnable, long)} instead.
	 */
	@Override
	@Deprecated(since = "1.7.10")
	public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay)
	{
		Logger.getLogger(LOGGER_NAME).warning("Consider using runTaskLater instead of scheduleSyncDelayTask");
		return runTaskLater(plugin, (Runnable) task, delay).getTaskId();
	}

	@Override
	public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task)
	{
		Logger.getLogger(LOGGER_NAME).warning("Consider using runTask instead of scheduleSyncDelayTask");
		return runTask(plugin, task).getTaskId();
	}

	/**
	 * @deprecated Use {@link #runTask(Plugin, Runnable)} instead.
	 */
	@Override
	@Deprecated(since = "1.7.10")
	public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task)
	{
		Logger.getLogger(LOGGER_NAME).warning("Consider using runTask instead of scheduleSyncDelayTask");
		return runTask(plugin, (Runnable) task).getTaskId();
	}

	@Override
	public int scheduleSyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period)
	{
		Logger.getLogger(LOGGER_NAME).warning("Consider using runTaskTimer instead of scheduleSyncRepeatingTask");
		return runTaskTimer(plugin, task, delay, period).getTaskId();
	}

	/**
	 * @deprecated Use {@link #runTaskTimer(Plugin, Runnable, long, long)} instead.
	 */
	@Override
	@Deprecated(since = "1.7.10")
	public int scheduleSyncRepeatingTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay, long period)
	{
		Logger.getLogger(LOGGER_NAME).warning("Consider using runTaskTimer instead of scheduleSyncRepeatingTask");
		return runTaskTimer(plugin, (Runnable) task, delay, period).getTaskId();
	}

	/**
	 * @deprecated Use {@link #runTaskLaterAsynchronously(Plugin, Runnable, long)}
	 *             instead.
	 */
	@Override
	@Deprecated(since = "1.5")
	public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay)
	{
		Logger.getLogger(LOGGER_NAME)
				.warning("Consider using runTaskLaterAsynchronously instead of scheduleAsyncDelayedTask");
		return runTaskLaterAsynchronously(plugin, task, delay).getTaskId();
	}

	/**
	 * @deprecated Use {@link #runTaskAsynchronously(Plugin, Runnable)} instead.
	 */
	@Override
	@Deprecated(since = "1.5")
	public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task)
	{
		Logger.getLogger(LOGGER_NAME)
				.warning("Consider using runTaskAsynchronously instead of scheduleAsyncDelayedTask");
		return runTaskAsynchronously(plugin, task).getTaskId();
	}

	/**
	 * @deprecated Use
	 *             {@link #runTaskTimerAsynchronously(Plugin, Runnable, long, long)}
	 *             instead.
	 */
	@Override
	@Deprecated(since = "1.5")
	public int scheduleAsyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period)
	{
		Logger.getLogger(LOGGER_NAME)
				.warning("Consider using runTaskTimerAsynchronously instead of scheduleAsyncRepeatingTask");
		return runTaskTimerAsynchronously(plugin, task, delay, period).getTaskId();
	}

	@Override
	public void cancelTask(int taskId)
	{
		scheduledTasks.cancelTask(taskId);
	}

	@Override
	public void cancelTasks(@NotNull Plugin plugin)
	{
		for (ScheduledTask task : scheduledTasks.getCurrentTaskList())
		{
			if (// Implicit null check
			Objects.equals(task.getOwner(), plugin))
			{
				task.cancel();
			}
		}
	}

	@Override
	public boolean isCurrentlyRunning(int taskId)
	{
		ScheduledTask task = scheduledTasks.getTask(taskId);
		return task != null && task.isRunning();
	}

	@Override
	public boolean isQueued(int taskId)
	{
		ScheduledTask task = scheduledTasks.getTask(taskId);
		return task != null && !task.isCancelled();
	}

	@Override
	@NotNull
	public List<BukkitWorker> getActiveWorkers()
	{
		synchronized (this.activeWorkers)
		{
			// Copying here is more efficient than using a CopyOnWriteArrayList,
			// since this method is not called as much as wrapTask(...)
			return List.copyOf(this.activeWorkers);
		}
	}

	/**
	 * Waits for all active workers to finish.
	 *
	 * @param timeout
	 *            The maximum time to wait.
	 * @param unit
	 *            The unit of the timeout.
	 * @return {@code true} if all workers finished, {@code false} if the timeout
	 *         was reached.
	 * @throws InterruptedException
	 *             If the thread was interrupted while waiting.
	 */
	public boolean waitActiveWorkersFinished(long timeout, @NotNull TimeUnit unit) throws InterruptedException
	{
		long end = System.currentTimeMillis() + unit.toMillis(timeout);
		synchronized (this.activeWorkers)
		{
			while (!this.activeWorkers.isEmpty())
			{
				long waitTime = end - System.currentTimeMillis();
				if (waitTime <= 0)
				{
					return false;
				}
				this.activeWorkers.wait(waitTime);
			}
		}
		return true;
	}

	@Override
	@NotNull
	public List<BukkitTask> getPendingTasks()
	{
		List<BukkitTask> pendingTasks = new ArrayList<>();
		for (ScheduledTask task : scheduledTasks.getCurrentTaskList())
		{
			if (!task.isCancelled())
			{
				pendingTasks.add(task);
			}
		}
		return Collections.unmodifiableList(pendingTasks);
	}

	@Override
	@NotNull
	public BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task)
	{
		long tick;
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			// There's no task registration here, no need to synchronize further
			tick = this.currentTick;
		}
		ScheduledTask scheduledTask = new ScheduledTask(id.getAndIncrement(), plugin, false, tick,
				new AsyncRunnable(task));
		pool.execute(wrapTask(scheduledTask));
		return scheduledTask;
	}

	@Override
	@NotNull
	public BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task)
	{
		return runTaskAsynchronously(plugin, (Runnable) task);
	}

	@Override
	@NotNull
	public BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay)
	{
		return runTaskLater(plugin, (Runnable) task, delay);
	}

	@Override
	@NotNull
	public BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay)
	{
		delay = Math.max(delay, 1);
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			ScheduledTask scheduledTask = new ScheduledTask(id.getAndIncrement(), plugin, false, currentTick + delay,
					new AsyncRunnable(task));
			scheduledTasks.addTask(scheduledTask);
			return scheduledTask;
		}
	}

	@Override
	@NotNull
	public BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay)
	{
		return runTaskLaterAsynchronously(plugin, (Runnable) task, delay);
	}

	@Override
	@NotNull
	public BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay,
			long period)
	{
		delay = Math.max(delay, 1);
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			RepeatingTask scheduledTask = new RepeatingTask(id.getAndIncrement(), plugin, false, currentTick + delay,
					period, new AsyncRunnable(task));
			scheduledTasks.addTask(scheduledTask);
			return scheduledTask;
		}
	}

	@Override
	@NotNull
	public BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay,
			long period)
	{
		return runTaskTimerAsynchronously(plugin, (Runnable) task, delay, period);
	}

	@Override
	public void runTask(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task)
	{
		runTaskLater(plugin, task, 0L);
	}

	@Override
	public void runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task)
	{
		long tick;
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			// There's no task registration here, no need to synchronize further
			tick = this.currentTick;
		}
		ScheduledTask scheduledTask = new ScheduledTask(this.id.getAndIncrement(), plugin, false, tick, task);
		pool.execute(wrapTask(scheduledTask));
	}

	@Override
	public void runTaskLater(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay)
	{
		delay = Math.max(delay, 1);
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			ScheduledTask scheduledTask = new ScheduledTask(id.getAndIncrement(), plugin, true, currentTick + delay,
					task);
			scheduledTasks.addTask(scheduledTask);
		}
	}

	@Override
	public void runTaskTimer(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay,
			long period)
	{
		delay = Math.max(delay, 1);
		synchronized (// See comment at the start of performOneTick()
		this)
		{
			RepeatingTask repeatingTask = new RepeatingTask(id.getAndIncrement(), plugin, true, currentTick + delay,
					period, task);
			scheduledTasks.addTask(repeatingTask);
		}
	}

	@Override
	@NotNull
	public Executor getMainThreadExecutor(@NotNull Plugin plugin)
	{
		Preconditions.checkNotNull(plugin, "Plugin cannot be null");
		return command ->
		{
			Preconditions.checkNotNull(command, "Command cannot be null");
			this.runTask(plugin, command);
		};
	}

	/**
	 * Gets the amount of thread currently executing asynchronous tasks.
	 *
	 * @return The amount of active task threads.
	 */
	protected int getActiveRunningCount()
	{
		return pool.getActiveCount();
	}

	/**
	 * Adds any active workers to the overdue tasks list.
	 */
	public void saveOverdueTasks()
	{
		this.overdueTasks.set(getActiveWorkers());
	}

	/**
	 * @return A list of overdue tasks saved by {@link #saveOverdueTasks()}.
	 */
	@NotNull
	@Unmodifiable
	public List<BukkitWorker> getOverdueTasks()
	{
		return this.overdueTasks.get();
	}

	/**
	 * Asserts that there were no overdue tasks from {@link #saveOverdueTasks()}.
	 *
	 * @deprecated Use other assertion methods if available.
	 */
	@SuppressWarnings("java:S1133")
	@Deprecated(forRemoval = true)
	public void assertNoOverdueTasks()
	{
		// Single read from AtomicReference
		List<BukkitWorker> tasks = this.overdueTasks.get();
		if (!tasks.isEmpty())
		{
			throw new AssertionFailedError("There are overdue tasks: " + tasks);
		}
	}

	private static class TaskList
	{

		// Do not directly access this field from outside TaskList, use a method
		@NotNull
		private final Map<Integer, ScheduledTask> tasks;

		private TaskList()
		{
			tasks = Collections.synchronizedMap(new HashMap<>());
		}

		/**
		 * Add a task but locks the Task list to other writes while adding it.
		 *
		 * @param task
		 *            the task to remove.
		 * @return true on success.
		 */
		private boolean addTask(@Nullable ScheduledTask task)
		{
			if (task == null)
			{
				return false;
			}
			tasks.put(task.getTaskId(), task);
			return true;
		}

		@Nullable
		private ScheduledTask getTask(int taskId)
		{
			return tasks.get(taskId);
		}

		@NotNull
		protected final List<ScheduledTask> getCurrentTaskList()
		{
			List<ScheduledTask> out = new ArrayList<>();
			synchronized (tasks)
			{
				if (!tasks.isEmpty())
				{
					out.addAll(tasks.values());
				}
			}
			return out;
		}

		protected int getScheduledTaskCount()
		{
			int scheduled = 0;
			synchronized (tasks)
			{
				if (tasks.isEmpty())
				{
					return 0;
				}
				for (ScheduledTask task : tasks.values())
				{
					if (task.isCancelled() || task.isRunning())
					{
						continue;
					}
					scheduled++;
				}
			}
			return scheduled;
		}

		protected boolean cancelTask(int taskID)
		{
			ScheduledTask task;
			synchronized (tasks)
			{
				task = tasks.get(taskID);
			}
			if (task != null)
			{
				task.cancel();
				return true;
			}
			return false;
		}

		protected void cancelRepeatingTasks()
		{
			List<RepeatingTask> toCancel = new ArrayList<>();
			synchronized (tasks)
			{
				for (ScheduledTask task : tasks.values())
				{
					if (task instanceof RepeatingTask repeatingTask)
					{
						toCancel.add(repeatingTask);
					}
				}
			}
			for (RepeatingTask task : toCancel)
			{
				task.cancel();
			}
		}
	}

	private final class AsyncRunnable implements Runnable
	{

		private final Runnable task;

		private AsyncRunnable(Runnable task)
		{
			this.task = task;
		}

		@Override
		public void run()
		{
			try
			{
				this.task.run();
			} catch (Exception t)
			{
				asyncException.set(t);
			}
		}
	}
}
