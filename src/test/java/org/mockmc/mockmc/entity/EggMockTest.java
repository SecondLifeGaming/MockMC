package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.inventory.ItemStackMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockMCExtension.class)
class EggMockTest
{

	@MockMCInject
	private EggMock egg;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.EGG, egg.getType());
	}

	@Test
	void testGetItemDefault()
	{
		assertEquals(new ItemStackMock(Material.EGG), egg.getItem());
	}

	@Test
	void testSetItem()
	{
		ItemStack item = new ItemStackMock(Material.DIAMOND);
		egg.setItem(item);
		assertEquals(Material.DIAMOND, egg.getItem().getType());
	}

	@Test
	void testSetItemNull()
	{
		assertThrows(NullPointerException.class, () -> egg.setItem(null));
	}

}
