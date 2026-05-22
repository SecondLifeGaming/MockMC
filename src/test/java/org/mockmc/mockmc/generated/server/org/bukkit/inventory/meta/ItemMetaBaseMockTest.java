// Auto-generated mechanical sanity test for ItemMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ItemMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.displayName());
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.getLocalizedName());
		assertSafeDefault(mock.getAsString());
		assertSafeDefault(mock.customName());
		assertSafeDefault(mock.getRarity());
		assertSafeDefault(mock.getTool());
		assertSafeDefault(mock.getLore());
		assertSafeDefault(mock.lore());
		assertSafeDefault(mock.getItemFlags());
		assertSafeDefault(mock.getDisplayNameComponent());
		assertSafeDefault(mock.itemName());
		assertSafeDefault(mock.getItemName());
		assertSafeDefault(mock.getLoreComponents());
		assertSafeDefault(mock.getCustomModelDataComponent());
		assertSafeDefault(mock.getEnchants());
		assertSafeDefault(mock.getTooltipStyle());
		assertSafeDefault(mock.getItemModel());
		assertSafeDefault(mock.getEnchantmentGlintOverride());
	}

	@Test
	void testSafeDefaultsPart2()
	{
		ItemMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDamageResistant());
		assertSafeDefault(mock.getDamageResistantTypes());
		assertSafeDefault(mock.getUseRemainder());
		assertSafeDefault(mock.getUseCooldown());
		assertSafeDefault(mock.getFood());
		assertSafeDefault(mock.getEquippable());
		assertSafeDefault(mock.getJukeboxPlayable());
		assertSafeDefault(mock.getAttributeModifiers());
		assertSafeDefault(mock.getAsComponentString());
		assertSafeDefault(mock.getCustomTagContainer());
		assertSafeDefault(mock.getCanDestroy());
		assertSafeDefault(mock.getCanPlaceOn());
		assertSafeDefault(mock.getDestroyableKeys());
		assertSafeDefault(mock.getPlaceableKeys());
	}

	private static class Stub implements ItemMetaBaseMock
	{
		@Override
		public ItemMeta clone()
		{
			return null;
		}
	}
}
