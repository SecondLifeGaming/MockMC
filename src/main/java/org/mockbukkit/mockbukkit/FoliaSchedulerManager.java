package org.mockbukkit.mockbukkit;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.RegionScheduler;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.scheduler.BukkitSchedulerMock;
import org.mockbukkit.mockbukkit.scheduler.paper.FoliaAsyncScheduler;
import org.mockbukkit.mockbukkit.scheduler.paper.FoliaGlobalRegionScheduler;
import org.mockbukkit.mockbukkit.scheduler.paper.FoliaRegionScheduler;

/**
 * Helper class to manage Folia schedulers for {@link ServerMock}.
 */
class FoliaSchedulerManager
{

	private final AsyncScheduler asyncScheduler;
	private final GlobalRegionScheduler globalRegionScheduler;
	private final RegionScheduler regionScheduler;

	FoliaSchedulerManager(@NotNull BukkitSchedulerMock scheduler)
	{
		this.asyncScheduler = new FoliaAsyncScheduler(scheduler);
		this.globalRegionScheduler = new FoliaGlobalRegionScheduler(scheduler);
		this.regionScheduler = new FoliaRegionScheduler(scheduler);
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

}
