package org.mockmc.mockmc.inventory;

import org.bukkit.inventory.InventoryHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@ExtendWith(MockMCExtension.class)
class LoomInventoryMockTest
{

	private LoomInventoryMock inventory;

	@BeforeEach
	void setUp()
	{
		inventory = new LoomInventoryMock((InventoryHolder) null);
	}

	@Test
	void testGetSnapshot()
	{
		InventoryMock snapshot = inventory.getSnapshot();

		assertInstanceOf(LoomInventoryMock.class, snapshot);
		assertNotSame(inventory, snapshot);
	}

}
