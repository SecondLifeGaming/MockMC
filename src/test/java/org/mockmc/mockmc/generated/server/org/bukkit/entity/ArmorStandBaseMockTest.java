// Auto-generated mechanical sanity test for ArmorStandBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class ArmorStandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ArmorStandBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEquipment());
		assertSafeDefault(mock.getItemInHand());
		assertSafeDefault(mock.getBoots());
		assertSafeDefault(mock.getLeggings());
		assertSafeDefault(mock.getChestplate());
		assertSafeDefault(mock.getHelmet());
		assertSafeDefault(mock.getBodyPose());
		assertSafeDefault(mock.getLeftArmPose());
		assertSafeDefault(mock.getRightArmPose());
		assertSafeDefault(mock.getLeftLegPose());
		assertSafeDefault(mock.getRightLegPose());
		assertSafeDefault(mock.getHeadPose());
		assertSafeDefault(mock.getDisabledSlots());
		assertSafeDefault(mock.getBodyRotations());
		assertSafeDefault(mock.getLeftArmRotations());
		assertSafeDefault(mock.getRightArmRotations());
		assertSafeDefault(mock.getLeftLegRotations());
		assertSafeDefault(mock.getRightLegRotations());
		assertSafeDefault(mock.getHeadRotations());
	}

	private static class Stub implements ArmorStandBaseMock
	{
	}
}
