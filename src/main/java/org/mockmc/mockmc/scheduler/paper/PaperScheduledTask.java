package org.mockmc.mockmc.scheduler.paper;

import com.google.common.base.Preconditions;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class PaperScheduledTask
		implements
			ScheduledTask,
			org.mockmc.mockmc.generated.server.io.papermc.paper.threadedregions.scheduler.ScheduledTaskBaseMock
{

	private final Plugin plugin;
	private final @NotNull Consumer<ScheduledTask> consumer;

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
		Preconditions.checkNotNull(consumer, "Consumer cannot be null");
		this.plugin = plugin;
		this.consumer = consumer;
	}

	@Override
	public @NotNull Plugin getOwningPlugin()
	{
		return this.plugin;
	}

	public void run()
	{
		this.consumer.accept(this);
	}

}
