// Auto-generated mechanical sanity test for SmithingInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SmithingInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SmithingInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getRecipe());
		assertSafeDefault(mock.getInputTemplate());
		assertSafeDefault(mock.getInputEquipment());
		assertSafeDefault(mock.getInputMineral());
	}

	private static class Stub implements SmithingInventoryBaseMock
	{
	}
}
