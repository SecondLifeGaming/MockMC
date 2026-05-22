// Auto-generated mechanical sanity test for EnchantmentWrapperBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.enchantments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class EnchantmentWrapperBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantmentWrapperBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEnchantment());
	}

	private static class Stub implements EnchantmentWrapperBaseMock
	{
	}
}
