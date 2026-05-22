package org.mockmc.mockmc.matcher.entity;

import org.bukkit.entity.Warden;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.mockmc.entity.WardenMock;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockmc.mockmc.matcher.entity.EntityStateMatcher.hasState;

@ExtendWith(MockMCExtension.class)
class EntityStateMatcherTest
{

	@MockMCInject
	private ServerMock server;
	private WardenMock warden;
	private PlayerMock player;

	@BeforeEach
	void setUp()
	{
		warden = (WardenMock) server.addSimpleWorld("world").spawn(server.addSimpleWorld("world").getSpawnLocation(),
				Warden.class);
		player = server.addPlayer();
	}

	@Test
	void testWithTarget()
	{
		warden.setTarget(player);
		assertTrue(hasState().withTarget(equalTo(player)).matches(warden));
		assertFalse(hasState().withTarget(org.hamcrest.Matchers.nullValue(org.bukkit.entity.LivingEntity.class))
				.matches(warden));
	}

	@Test
	void testIsAware()
	{
		warden.setAware(false);
		assertTrue(hasState().isAware(false).matches(warden));
		assertFalse(hasState().isAware(true).matches(warden));
	}

	@Test
	void testWithFilter()
	{
		warden.increaseAnger(player, 50);
		assertTrue(hasState().withFilter(w -> ((Warden) w).getAnger(player) == 50, "anger 50").matches(warden));
	}
}
