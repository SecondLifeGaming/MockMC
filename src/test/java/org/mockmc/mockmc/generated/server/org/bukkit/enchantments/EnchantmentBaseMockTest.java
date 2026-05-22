// Auto-generated mechanical sanity test for EnchantmentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.enchantments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class EnchantmentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantmentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.getExclusiveWith());
		assertSafeDefault(mock.getSupportedItems());
		assertSafeDefault(mock.getPrimaryItems());
		assertSafeDefault(mock.translationKey());
		assertSafeDefault(mock.getRarity());
		assertSafeDefault(mock.getActiveSlotGroups());
		assertSafeDefault(mock.getItemTarget());
		assertSafeDefault(mock.getActiveSlots());
	}

	private static class Stub implements EnchantmentBaseMock
	{
	}
}
