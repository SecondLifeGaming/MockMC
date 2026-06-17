// Auto-generated mechanical sanity test for EnchantingInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EnchantingInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantingInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSecondary());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getItem());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EnchantingInventoryBaseMock
	{
	}
}
