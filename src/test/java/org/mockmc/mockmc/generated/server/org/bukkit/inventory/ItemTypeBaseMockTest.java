// Auto-generated mechanical sanity test for ItemTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ItemTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItemMetaClass());
		assertSafeDefault(mock.getDefaultDataTypes());
		assertSafeDefault(mock.getBlockType());
		assertSafeDefault(mock.getTranslationKey());
		assertSafeDefault(mock.getDefaultAttributeModifiers());
		assertSafeDefault(mock.getCraftingRemainingItem());
		assertSafeDefault(mock.asMaterial());
		assertSafeDefault(mock.getCreativeCategory());
		assertSafeDefault(mock.getItemRarity());
		assertSafeDefault(mock.typed());
		assertSafeDefault(mock.createItemStack());
	}

	private static class Stub implements ItemTypeBaseMock
	{
	}
}
