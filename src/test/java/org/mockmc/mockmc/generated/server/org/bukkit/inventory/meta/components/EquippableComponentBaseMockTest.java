// Auto-generated mechanical sanity test for EquippableComponentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EquippableComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EquippableComponentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSlot());
		assertSafeDefault(mock.getModel());
		assertSafeDefault(mock.getCameraOverlay());
		assertSafeDefault(mock.getAllowedEntities());
		assertSafeDefault(mock.getShearingSound());
		assertSafeDefault(mock.getEquipSound());
	}

	private static class Stub implements EquippableComponentBaseMock
	{
	}
}
