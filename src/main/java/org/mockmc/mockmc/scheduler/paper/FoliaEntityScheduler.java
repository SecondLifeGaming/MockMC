package org.mockmc.mockmc.scheduler.paper;

import com.google.common.base.Preconditions;
import io.papermc.paper.threadedregions.scheduler.EntityScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.scheduler.BukkitSchedulerMock;

import java.util.function.Consumer;

public final class FoliaEntityScheduler implements EntityScheduler
{

	private static final String PLUGIN_CANNOT_BE_NULL = "plugin cannot be null";
	private static final String TASK_CANNOT_BE_NULL = "task cannot be null";
	private static final String RUNNABLE_CANNOT_BE_NULL = "runnable cannot be null";

	private final org.mockmc.mockmc.ServerMock server;
	private final BukkitSchedulerMock scheduler;
	private final Entity entity;

	public FoliaEntityScheduler(@NotNull org.mockmc.mockmc.ServerMock server, @NotNull BukkitSchedulerMock scheduler,
			@NotNull Entity entity)
	{
		this.server = server;
		this.scheduler = scheduler;
		this.entity = entity;
	}

	@Override
	public boolean execute(@NotNull Plugin plugin, @NotNull Runnable run, @Nullable Runnable retired, long delayTicks)
	{

		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(run, RUNNABLE_CANNOT_BE_NULL);

		scheduler.runTaskLater(plugin, () ->
		{
			if (entity.isValid())
			{
				run.run();
			} else if (retired != null)
			{
				retired.run();
			}
		}, delayTicks);

		return entity.isValid();
	}

	@Override
	public @Nullable ScheduledTask run(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
			@Nullable Runnable retired)
	{

		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(task, TASK_CANNOT_BE_NULL);

		PaperScheduledTask.TaskContext context = new PaperScheduledTask.TaskContext(PaperScheduledTask.TaskType.ENTITY,
				entity, null, null, null);
		PaperScheduledTask scheduledTask = new PaperScheduledTask(plugin, task, context);
		server.registerFoliaTask(scheduledTask);

		scheduler.runTask(plugin, () ->
		{
			if (entity.isValid())
			{
				scheduledTask.run();
			} else if (retired != null)
			{
				retired.run();
			}
		});

		return scheduledTask;
	}

	@Override
	public @Nullable ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
			@Nullable Runnable retired, long delayTicks)
	{

		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(task, TASK_CANNOT_BE_NULL);

		PaperScheduledTask.TaskContext context = new PaperScheduledTask.TaskContext(PaperScheduledTask.TaskType.ENTITY,
				entity, null, null, null);
		PaperScheduledTask scheduledTask = new PaperScheduledTask(plugin, task, context);
		server.registerFoliaTask(scheduledTask);

		scheduler.runTaskLater(plugin, () ->
		{
			if (entity.isValid())
			{
				scheduledTask.run();
			} else if (retired != null)
			{
				retired.run();
			}
		}, delayTicks);

		return scheduledTask;
	}

	@Override
	public @Nullable ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
			@Nullable Runnable retired, long initialDelayTicks, long periodTicks)
	{

		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(task, TASK_CANNOT_BE_NULL);

		PaperScheduledTask.TaskContext context = new PaperScheduledTask.TaskContext(PaperScheduledTask.TaskType.ENTITY,
				entity, null, null, null);
		PaperScheduledTask scheduledTask = new PaperScheduledTask(plugin, task, context);
		server.registerFoliaTask(scheduledTask);

		scheduler.runTaskTimer(plugin, () ->
		{
			if (entity.isValid())
			{
				scheduledTask.run();
			} else if (retired != null)
			{
				retired.run();
			}
		}, initialDelayTicks, periodTicks);

		return scheduledTask;
	}
}
