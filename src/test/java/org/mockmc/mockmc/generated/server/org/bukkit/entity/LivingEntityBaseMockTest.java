// Auto-generated mechanical sanity test for LivingEntityBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class LivingEntityBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LivingEntityBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCategory());
		assertSafeDefault(mock.getEquipment());
		assertSafeDefault(mock.getActiveItemHand());
		assertSafeDefault(mock.getEyeLocation());
		assertSafeDefault(mock.getItemInUse());
		assertSafeDefault(mock.getKiller());
		assertSafeDefault(mock.getActivePotionEffects());
		assertSafeDefault(mock.getLeashHolder());
		assertSafeDefault(mock.getCollidableExemptions());
		assertSafeDefault(mock.getHurtSound());
		assertSafeDefault(mock.getDeathSound());
		assertSafeDefault(mock.getFallDamageSoundSmall());
		assertSafeDefault(mock.getFallDamageSoundBig());
		assertSafeDefault(mock.getActiveItem());
		assertSafeDefault(mock.getHandRaised());
		assertSafeDefault(mock.getCombatTracker());
		assertSafeDefault(mock.getWaypointStyle());
		assertSafeDefault(mock.getWaypointColor());
	}

	private static class Stub implements LivingEntityBaseMock
	{
	}
}
