package org.mockmc.mockmc.scheduler.paper;

import com.google.common.base.Preconditions;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class PaperScheduledTask
		implements
			ScheduledTask,
			org.mockmc.mockmc.generated.server.io.papermc.paper.threadedregions.scheduler.ScheduledTaskBaseMock
{

	private final Plugin plugin;
	private final @NotNull Consumer<ScheduledTask> consumer;
	private final @NotNull TaskContext context;

	public enum TaskType
	{
		GLOBAL, REGION, ENTITY, ASYNC
	}

	public record TaskContext(@NotNull TaskType type, @Nullable Entity entity, @Nullable World world, Integer chunkX,
			Integer chunkZ)
	{
	}

	/**
	 * Constructs a new {@link org.mockmc.mockmc.scheduler.paper.PaperScheduledTask}
	 * with the provided parameters.
	 *
	 * @param plugin
	 *            The plugin owning the task.
	 * @param consumer
	 *            The consumer to run.
	 */
	public PaperScheduledTask(Plugin plugin, @NotNull Consumer<ScheduledTask> consumer)
	{
		this(plugin, consumer, new TaskContext(TaskType.ASYNC, null, null, null, null));
	}

	public PaperScheduledTask(Plugin plugin, @NotNull Consumer<ScheduledTask> consumer, @NotNull TaskContext context)
	{
		Preconditions.checkNotNull(consumer, "Consumer cannot be null");
		Preconditions.checkNotNull(context, "Context cannot be null");
		this.plugin = plugin;
		this.consumer = consumer;
		this.context = context;
	}

	@Override
	public @NotNull Plugin getOwningPlugin()
	{
		return this.plugin;
	}

	public @NotNull TaskContext getContext()
	{
		return this.context;
	}

	public void run()
	{
		this.consumer.accept(this);
	}

}
