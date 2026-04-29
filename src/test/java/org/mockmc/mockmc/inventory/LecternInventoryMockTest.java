package org.mockmc.mockmc.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.LecternInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.block.state.LecternStateMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@ExtendWith(MockMCExtension.class)
class LecternInventoryMockTest
{

	private LecternInventoryMock inventory;
	private LecternStateMock block;

	@BeforeEach
	void setup()
	{
		new ServerMock();
		block = new LecternStateMock(Material.LECTERN);
		inventory = new LecternInventoryMock(block);
	}

	@Test
	void testGetSnapshot()
	{
		assertInstanceOf(LecternInventory.class, inventory.getSnapshot());
		assertNotSame(inventory, inventory.getSnapshot());
	}

	@Test
	void testGetHolder()
	{
		assertEquals(block, inventory.getHolder());
	}

}
