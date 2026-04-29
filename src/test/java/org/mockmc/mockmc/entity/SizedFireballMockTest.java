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
class SizedFireballMockTest
{

	@MockMCInject
	private SizedFireballMock fireball;

	@Test
	void testGetDisplayItem()
	{
		ItemStack item = new ItemStackMock(Material.FIRE_CHARGE);
		assertEquals(item, fireball.getDisplayItem());
	}

	@Test
	void testSetDisplayItem()
	{
		ItemStack item = new ItemStackMock(Material.FIRE_CHARGE);
		fireball.setDisplayItem(item);
		assertEquals(item, fireball.getDisplayItem());
	}

	@Test
	void testSetDisplayItem_Null()
	{
		assertThrows(NullPointerException.class, () -> fireball.setDisplayItem(null));
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.FIREBALL, fireball.getType());
	}

}
