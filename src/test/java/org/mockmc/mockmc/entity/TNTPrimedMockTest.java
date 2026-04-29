package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockmc.mockmc.matcher.plugin.PluginManagerFiredEventClassMatcher.hasFiredEventInstance;
import static org.mockmc.mockmc.matcher.plugin.PluginManagerFiredEventClassMatcher.hasNotFiredEventInstance;

@ExtendWith(MockMCExtension.class)
class TNTPrimedMockTest
{

	@MockMCInject
	private ServerMock server;
	@MockMCInject
	private TNTPrimedMock tntPrimed;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.TNT, tntPrimed.getType());
	}

	@Test
	void testGetFuseTicksDefault()
	{
		assertEquals(80, tntPrimed.getFuseTicks());
	}

	@Test
	void testSetFuseTicks()
	{
		tntPrimed.setFuseTicks(10);
		assertEquals(10, tntPrimed.getFuseTicks());
	}

	@Test
	void testGetSourceDefault()
	{
		assertNull(tntPrimed.getSource());
	}

	@Test
	void testSetSource()
	{
		PlayerMock playerMock = server.addPlayer();
		tntPrimed.setSource(playerMock);
		assertEquals(playerMock, tntPrimed.getSource());
	}

	@Test
	void testSetSourceNotLivingEntity()
	{
		tntPrimed.setSource(new ExperienceOrbMock(server, UUID.randomUUID()));
		assertNull(tntPrimed.getSource());
	}

	@Test
	void testGetYieldDefault()
	{
		assertEquals(4, tntPrimed.getYield());
	}

	@Test
	void testSetYield()
	{
		tntPrimed.setYield(2);
		assertEquals(2, tntPrimed.getYield());
	}

	@Test
	void testIsIncendiaryDefault()
	{
		assertFalse(tntPrimed.isIncendiary());
	}

	@Test
	void testSetIsIncendiary()
	{
		tntPrimed.setIsIncendiary(true);
		assertTrue(tntPrimed.isIncendiary());
	}

	@Test
	void testExplosion()
	{
		tntPrimed.tick(tntPrimed.getFuseTicks());
		assertTrue(tntPrimed.isDead());
		assertThat(server.getPluginManager(), hasFiredEventInstance(ExplosionPrimeEvent.class));
	}

	@Test
	void testOneTickNoExplosion()
	{
		tntPrimed.tick();
		assertFalse(tntPrimed.isDead());
		assertThat(server.getPluginManager(), hasNotFiredEventInstance(ExplosionPrimeEvent.class));
	}

	@Test
	void testOneTickExplosion()
	{
		tntPrimed.setFuseTicks(1);
		tntPrimed.tick();
		assertTrue(tntPrimed.isDead());
		assertThat(server.getPluginManager(), hasFiredEventInstance(ExplosionPrimeEvent.class));
	}

}
