package org.mockmc.mockmc.scheduler.paper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMC;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.plugin.PluginMock;
import org.mockmc.mockmc.scheduler.BukkitSchedulerMock;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockMCExtension.class)
class FoliaGlobalRegionSchedulerTest
{

	@MockMCInject
	private ServerMock server;

	@MockMCInject
	private BukkitSchedulerMock bukkitScheduler;

	private FoliaGlobalRegionScheduler scheduler;
	private PluginMock plugin;

	@BeforeEach
	void setUp()
	{
		scheduler = new FoliaGlobalRegionScheduler(bukkitScheduler);
		plugin = MockMC.createMockPlugin();
	}

	@Test
	void run_RunsTask()
	{
		CountDownLatch latch = new CountDownLatch(1);
		scheduler.run(plugin, task -> latch.countDown());
		bukkitScheduler.performOneTick();
		assertEquals(0, latch.getCount());
	}

	@Test
	void execute_RunsTask()
	{
		CountDownLatch latch = new CountDownLatch(1);
		scheduler.execute(plugin, latch::countDown);
		bukkitScheduler.performOneTick();
		assertEquals(0, latch.getCount());
	}

	@Test
	void runDelayed_RunsLater()
	{
		CountDownLatch latch = new CountDownLatch(1);
		scheduler.runDelayed(plugin, task -> latch.countDown(), 2);
		bukkitScheduler.performOneTick();
		assertEquals(1, latch.getCount());
		bukkitScheduler.performOneTick();
		assertEquals(0, latch.getCount());
	}

	@Test
	void runAtFixedRate_RunsRepeatedly()
	{
		CountDownLatch latch = new CountDownLatch(3);
		scheduler.runAtFixedRate(plugin, task -> latch.countDown(), 1, 1);
		bukkitScheduler.performTicks(3);
		assertEquals(0, latch.getCount());
	}

	@Test
	void nullPlugin_Throws()
	{
		assertThrows(NullPointerException.class, () -> scheduler.run(null, task ->
		{
		}));
	}

	@Test
	void nullTask_Throws()
	{
		assertThrows(NullPointerException.class, () -> scheduler.run(plugin, null));
	}
}
