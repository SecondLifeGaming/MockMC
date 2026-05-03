package org.mockmc.mockmc.scheduler.paper;

import com.google.common.base.Preconditions;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.scheduler.BukkitSchedulerMock;

import java.util.function.Consumer;

public final class FoliaGlobalRegionScheduler implements GlobalRegionScheduler
{

	private static final String PLUGIN_CANNOT_BE_NULL = "plugin cannot be null";
	private static final String TASK_CANNOT_BE_NULL = "task cannot be null";
	private static final String RUNNABLE_CANNOT_BE_NULL = "runnable cannot be null";

	private final org.mockmc.mockmc.ServerMock server;
	private final BukkitSchedulerMock scheduler;

	public FoliaGlobalRegionScheduler(@NotNull org.mockmc.mockmc.ServerMock server,
			@NotNull BukkitSchedulerMock scheduler)
	{
		this.server = server;
		this.scheduler = scheduler;
	}

	@Override
	public @NotNull ScheduledTask run(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(task, TASK_CANNOT_BE_NULL);

		PaperScheduledTask.TaskContext context = new PaperScheduledTask.TaskContext(PaperScheduledTask.TaskType.GLOBAL,
				null, null, null, null);
		PaperScheduledTask scheduledTask = new PaperScheduledTask(plugin, task, context);
		server.registerFoliaTask(scheduledTask);
		scheduler.runTask(plugin, scheduledTask::run);
		return scheduledTask;
	}

	@Override
	public void execute(@NotNull Plugin plugin, @NotNull Runnable run)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(run, RUNNABLE_CANNOT_BE_NULL);

		scheduler.runTask(plugin, run);
	}

	@Override
	public @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
			long delayTicks)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(task, TASK_CANNOT_BE_NULL);

		PaperScheduledTask.TaskContext context = new PaperScheduledTask.TaskContext(PaperScheduledTask.TaskType.GLOBAL,
				null, null, null, null);
		PaperScheduledTask scheduledTask = new PaperScheduledTask(plugin, task, context);
		server.registerFoliaTask(scheduledTask);
		scheduler.runTaskLater(plugin, scheduledTask::run, delayTicks);
		return scheduledTask;
	}

	@Override
	public @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
			long initialDelayTicks, long periodTicks)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(task, TASK_CANNOT_BE_NULL);

		PaperScheduledTask.TaskContext context = new PaperScheduledTask.TaskContext(PaperScheduledTask.TaskType.GLOBAL,
				null, null, null, null);
		PaperScheduledTask scheduledTask = new PaperScheduledTask(plugin, task, context);
		scheduler.runTaskTimer(plugin, scheduledTask::run, initialDelayTicks, periodTicks);
		return scheduledTask;
	}

	@Override
	public void cancelTasks(@NotNull Plugin plugin)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		scheduler.cancelTasks(plugin);
	}
}
