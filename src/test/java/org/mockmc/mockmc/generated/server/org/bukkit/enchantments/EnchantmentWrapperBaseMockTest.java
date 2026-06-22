// Auto-generated mechanical sanity test for EnchantmentWrapperBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.enchantments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class EnchantmentWrapperBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantmentWrapperBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getEnchantment());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EnchantmentWrapperBaseMock
	{
	}
}
