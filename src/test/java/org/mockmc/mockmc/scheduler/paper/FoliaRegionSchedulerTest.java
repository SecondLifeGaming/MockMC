package org.mockmc.mockmc.scheduler.paper;

import org.bukkit.World;
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
class FoliaRegionSchedulerTest
{

	@MockMCInject
	private ServerMock server;

	@MockMCInject
	private BukkitSchedulerMock bukkitScheduler;

	private FoliaRegionScheduler scheduler;
	private PluginMock plugin;
	private World world;

	@BeforeEach
	void setUp()
	{
		scheduler = new FoliaRegionScheduler(server, bukkitScheduler);
		plugin = MockMC.createMockPlugin();
		world = server.addSimpleWorld("world");
	}

	@Test
	void run_RunsTask()
	{
		CountDownLatch latch = new CountDownLatch(1);
		scheduler.run(plugin, world, 0, 0, task -> latch.countDown());
		bukkitScheduler.performOneTick();
		assertEquals(0, latch.getCount());
	}

	@Test
	void execute_RunsTask()
	{
		CountDownLatch latch = new CountDownLatch(1);
		scheduler.execute(plugin, world, 0, 0, latch::countDown);
		bukkitScheduler.performOneTick();
		assertEquals(0, latch.getCount());
	}

	@Test
	void runDelayed_RunsLater()
	{
		CountDownLatch latch = new CountDownLatch(1);
		scheduler.runDelayed(plugin, world, 0, 0, task -> latch.countDown(), 2);
		bukkitScheduler.performOneTick();
		assertEquals(1, latch.getCount());
		bukkitScheduler.performOneTick();
		assertEquals(0, latch.getCount());
	}

	@Test
	void runAtFixedRate_RunsRepeatedly()
	{
		CountDownLatch latch = new CountDownLatch(3);
		scheduler.runAtFixedRate(plugin, world, 0, 0, task -> latch.countDown(), 1, 1);
		bukkitScheduler.performTicks(3);
		assertEquals(0, latch.getCount());
	}

	@Test
	void nullPlugin_Throws()
	{
		assertThrows(NullPointerException.class, () -> scheduler.run(null, world, 0, 0, task ->
		{
		}));
	}

	@Test
	void nullWorld_Throws()
	{
		assertThrows(NullPointerException.class, () -> scheduler.run(plugin, null, 0, 0, task ->
		{
		}));
	}

	@Test
	void nullTask_Throws()
	{
		assertThrows(NullPointerException.class, () -> scheduler.run(plugin, world, 0, 0, null));
	}
}
