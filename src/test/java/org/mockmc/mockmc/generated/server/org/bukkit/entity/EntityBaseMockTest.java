// Auto-generated mechanical sanity test for EntityBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class EntityBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EntityBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.copy());
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getAsString());
		assertSafeDefault(mock.getScheduler());
		assertSafeDefault(mock.getWorld());
		assertSafeDefault(mock.spigot());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getChunk());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getOrigin());
		assertSafeDefault(mock.getPistonMoveReaction());
		assertSafeDefault(mock.getBoundingBox());
		assertSafeDefault(mock.getTrackedPlayers());
		assertSafeDefault(mock.getFacing());
		assertSafeDefault(mock.getVelocity());
		assertSafeDefault(mock.getVisualFire());
		assertSafeDefault(mock.getPassenger());
		assertSafeDefault(mock.getPassengers());
		assertSafeDefault(mock.getPickItemStack());
	}

	@Test
	void testSafeDefaultsPart2()
	{
		EntityBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLastDamageCause());
		assertSafeDefault(mock.getSwimSound());
		assertSafeDefault(mock.getSwimSplashSound());
		assertSafeDefault(mock.getSwimHighSpeedSplashSound());
		assertSafeDefault(mock.getVehicle());
		assertSafeDefault(mock.getTrackedBy());
		assertSafeDefault(mock.getScoreboardTags());
		assertSafeDefault(mock.getPose());
		assertSafeDefault(mock.getSpawnCategory());
		assertSafeDefault(mock.createSnapshot());
		assertSafeDefault(mock.teamDisplayName());
		assertSafeDefault(mock.getEntitySpawnReason());
		assertSafeDefault(mock.getScoreboardEntryName());
	}

	private static class Stub implements EntityBaseMock
	{
	}
}
