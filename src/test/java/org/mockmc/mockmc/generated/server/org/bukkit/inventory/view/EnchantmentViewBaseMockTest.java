// Auto-generated mechanical sanity test for EnchantmentViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class EnchantmentViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantmentViewBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getTopInventory());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getOffers());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EnchantmentViewBaseMock
	{
	}
}
