// Auto-generated mechanical sanity test for CraftingRecipeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CraftingRecipeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CraftingRecipeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getGroup());
		assertSafeDefault(mock.getCategory());
	}

	private static class Stub implements CraftingRecipeBaseMock
	{
	}
}
