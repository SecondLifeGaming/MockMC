// Auto-generated mechanical sanity test for CraftingInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CraftingInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CraftingInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getRecipe());
		assertSafeDefault(mock.getMatrix());
	}

	private static class Stub implements CraftingInventoryBaseMock
	{
	}
}
