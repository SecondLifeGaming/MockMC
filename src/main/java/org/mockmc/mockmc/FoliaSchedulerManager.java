package org.mockmc.mockmc;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.RegionScheduler;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.scheduler.BukkitSchedulerMock;
import org.mockmc.mockmc.scheduler.paper.FoliaAsyncScheduler;
import org.mockmc.mockmc.scheduler.paper.FoliaGlobalRegionScheduler;
import org.mockmc.mockmc.scheduler.paper.FoliaRegionScheduler;
import org.mockmc.mockmc.scheduler.paper.PaperScheduledTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Helper class to manage Folia schedulers for {@link ServerMock}.
 */
class FoliaSchedulerManager
{

	private final AsyncScheduler asyncScheduler;
	private final GlobalRegionScheduler globalRegionScheduler;
	private final RegionScheduler regionScheduler;
	private final List<PaperScheduledTask> tasks = Collections.synchronizedList(new ArrayList<>());

	FoliaSchedulerManager(@NotNull ServerMockBase server, @NotNull BukkitSchedulerMock scheduler)
	{
		this.asyncScheduler = new FoliaAsyncScheduler(scheduler);
		this.globalRegionScheduler = new FoliaGlobalRegionScheduler((ServerMock) server, scheduler);
		this.regionScheduler = new FoliaRegionScheduler((ServerMock) server, scheduler);
	}

	@NotNull
	AsyncScheduler getAsyncScheduler()
	{
		return this.asyncScheduler;
	}

	@NotNull
	GlobalRegionScheduler getGlobalRegionScheduler()
	{
		return this.globalRegionScheduler;
	}

	@NotNull
	RegionScheduler getRegionScheduler()
	{
		return this.regionScheduler;
	}

	void registerTask(@NotNull PaperScheduledTask task)
	{
		this.tasks.add(task);
	}

	@NotNull
	List<PaperScheduledTask> getTasks()
	{
		return new ArrayList<>(this.tasks);
	}

	@NotNull
	List<PaperScheduledTask> getTasks(@NotNull PaperScheduledTask.TaskType type)
	{
		return this.tasks.stream().filter(task -> task.getContext().type() == type).toList();
	}

	@NotNull
	List<PaperScheduledTask> getTasks(@NotNull Entity entity)
	{
		return this.tasks.stream().filter(task -> entity.equals(task.getContext().entity())).toList();
	}

	@NotNull
	List<PaperScheduledTask> getTasks(@NotNull World world, int chunkX, int chunkZ)
	{
		return this.tasks.stream().filter(task -> world.equals(task.getContext().world())
				&& task.getContext().chunkX() == chunkX && task.getContext().chunkZ() == chunkZ).toList();
	}

}
