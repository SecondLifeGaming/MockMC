// Auto-generated mechanical sanity test for EntityEquipmentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class EntityEquipmentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EntityEquipmentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHolder());
		assertSafeDefault(mock.getItemInHand());
		assertSafeDefault(mock.getBoots());
		assertSafeDefault(mock.getLeggings());
		assertSafeDefault(mock.getChestplate());
		assertSafeDefault(mock.getHelmet());
		assertSafeDefault(mock.getItemInMainHand());
		assertSafeDefault(mock.getItemInOffHand());
		assertSafeDefault(mock.getArmorContents());
	}

	private static class Stub implements EntityEquipmentBaseMock
	{
	}
}
