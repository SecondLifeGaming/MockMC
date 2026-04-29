package org.mockmc.mockmc.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.world.WorldMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockMCExtension.class)
class HorseInventoryMockTest
{

	@MockMCInject
	private ServerMock server;
	@MockMCInject
	private WorldMock world;
	private HorseInventoryMock inventory;

	@BeforeEach
	void setUp()
	{
		inventory = new HorseInventoryMock(null);
	}

	@Test
	void getSize_Default_2()
	{
		assertEquals(2, inventory.getSize());
	}

	@Test
	void setArmor()
	{
		assertNull(inventory.getArmor());
		ItemStack item = new ItemStackMock(Material.IRON_HORSE_ARMOR);

		inventory.setArmor(item);

		assertEquals(item, inventory.getArmor());
	}

	@Test
	void setArmor_SetsItemInSlot()
	{
		ItemStack item = new ItemStackMock(Material.IRON_HORSE_ARMOR);

		inventory.setArmor(item);

		assertEquals(item, inventory.getItem(1));
	}

}
