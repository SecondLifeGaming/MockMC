package org.mockmc.mockmc.scheduler.paper;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.mockmc.MockMC;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockMCExtension.class)
class FoliaAssertionTest
{

	@MockMCInject
	private ServerMock server;
	private Plugin plugin;

	@BeforeEach
	void setUp()
	{
		plugin = MockMC.createMockPlugin();
	}

	@Test
	void assertScheduledOnEntityRegion_Scheduled_Passes()
	{
		PlayerMock player = server.addPlayer();
		player.getScheduler().run(plugin, task ->
		{
		}, null);

		server.assertScheduledOnEntityRegion(player);
	}

	@Test
	void assertScheduledOnEntityRegion_NotScheduled_Throws()
	{
		PlayerMock player = server.addPlayer();

		assertThrows(AssertionError.class, () -> server.assertScheduledOnEntityRegion(player));
	}

	@Test
	void assertScheduledOnRegion_Scheduled_Passes()
	{
		World world = server.addSimpleWorld("test");
		server.getRegionScheduler().run(plugin, world, 1, 2, task ->
		{
		});

		server.assertScheduledOnRegion(world, 1, 2);
	}

	@Test
	void assertScheduledOnGlobalRegion_Scheduled_Passes()
	{
		server.getGlobalRegionScheduler().run(plugin, task ->
		{
		});

		server.assertScheduledOnGlobalRegion();
	}

}
