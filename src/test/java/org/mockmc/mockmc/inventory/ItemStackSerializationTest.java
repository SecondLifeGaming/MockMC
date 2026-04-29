package org.mockmc.mockmc.inventory;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class ItemStackSerializationTest
{

	@Test
	void testSerialize_WithComponent()
	{
		ItemStackMock itemStack = new ItemStackMock(Material.DIAMOND_PICKAXE);
		Component itemName = Component.translatable("item.minecraft.acacia_boat");
		itemStack.setData(DataComponentTypes.ITEM_NAME, itemName);

		Map<String, Object> serialized = itemStack.serialize();

		assertTrue(serialized.containsKey("components"));
		Map<String, Object> components = (Map<String, Object>) serialized.get("components");
		assertTrue(components.containsKey("minecraft:item_name"));
		assertEquals("{\"translate\":\"item.minecraft.acacia_boat\"}", components.get("minecraft:item_name"));
	}

	@Test
	void testSerialize_WithItemMeta()
	{
		ItemStackMock itemStack = new ItemStackMock(Material.DIAMOND_PICKAXE);
		var meta = itemStack.getItemMeta();
		meta.displayName(Component.translatable("meta.name"));
		itemStack.setItemMeta(meta);

		Map<String, Object> serialized = itemStack.serialize();

		assertTrue(serialized.containsKey("meta"));
		Map<String, Object> metaMap = (Map<String, Object>) serialized.get("meta");
		assertEquals("{\"translate\":\"meta.name\"}", metaMap.get("display-name"));
	}

}
