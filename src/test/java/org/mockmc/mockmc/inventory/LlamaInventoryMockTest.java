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
class LlamaInventoryMockTest
{

	@MockMCInject
	private ServerMock server;
	@MockMCInject
	private WorldMock world;
	private LlamaInventoryMock inventory;

	@BeforeEach
	void setUp()
	{
		inventory = new LlamaInventoryMock(null);
	}

	@Test
	void getSize_Default_2()
	{
		assertEquals(2, inventory.getSize());
	}

	@Test
	void setDecor()
	{
		assertNull(inventory.getDecor());
		ItemStack item = new ItemStackMock(Material.IRON_HORSE_ARMOR);

		inventory.setDecor(item);

		assertEquals(item, inventory.getDecor());
	}

	@Test
	void setDecor_SetsItemInSlot()
	{
		ItemStack item = new ItemStackMock(Material.IRON_HORSE_ARMOR);

		inventory.setDecor(item);

		assertEquals(item, inventory.getItem(1));
	}

}
