package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockmc.mockmc.matcher.plugin.PluginManagerFiredEventClassMatcher.hasFiredEventInstance;

@ExtendWith(MockMCExtension.class)
class ExplosiveMinecartMockTest
{

	@MockMCInject
	private ServerMock server;
	@MockMCInject
	private ExplosiveMinecart minecart;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.TNT_MINECART, minecart.getType());
	}

	@Test
	void testGetMinecartMaterial()
	{
		assertEquals(Material.TNT_MINECART, minecart.getMinecartMaterial());
	}

	@Test
	void testGetFuseTicksDefault()
	{
		assertEquals(-1, minecart.getFuseTicks());
	}

	@Test
	void testSetFuseTicks()
	{
		minecart.setFuseTicks(80);
		assertEquals(80, minecart.getFuseTicks());
	}

	@Test
	void testIgnite()
	{
		minecart.ignite();
		assertEquals(80, minecart.getFuseTicks());
	}

	@Test
	void testIsIgnited()
	{
		assertFalse(minecart.isIgnited());
		minecart.ignite();
		assertTrue(minecart.isIgnited());
	}

	@Test
	void testExplode()
	{
		minecart.explode();
		assertTrue(minecart.isDead());
		assertThat(server.getPluginManager(), hasFiredEventInstance(ExplosionPrimeEvent.class));
	}

	@Test
	void testExplodePower()
	{
		minecart.explode(2.5f);
		assertTrue(minecart.isDead());
		assertThat(server.getPluginManager(), hasFiredEventInstance(ExplosionPrimeEvent.class));
	}

	@Test
	void testExplodePowerTooSmall()
	{
		assertThrows(IllegalArgumentException.class, () -> minecart.explode(-1.0f));
	}

	@Test
	void testExplodePowerTooBig()
	{
		assertThrows(IllegalArgumentException.class, () -> minecart.explode(6.0f));
	}

}
