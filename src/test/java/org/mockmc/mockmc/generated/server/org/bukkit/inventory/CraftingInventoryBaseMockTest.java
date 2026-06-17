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
		try
		{
			assertSafeDefault(mock.getResult());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getMatrix());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getRecipe());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements CraftingInventoryBaseMock
	{
	}
}
