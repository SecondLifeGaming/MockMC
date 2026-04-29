package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.inventory.ItemStackMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockMCExtension.class)
class SnowballMockTest
{

	@MockMCInject
	private Snowball snowball;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.SNOWBALL, snowball.getType());
	}

	@Test
	void testGetItem()
	{
		assertEquals(Material.SNOWBALL, snowball.getItem().getType());
		assertEquals(1, snowball.getItem().getAmount());
	}

	@Test
	void testSetItem()
	{
		snowball.setItem(new ItemStackMock(Material.DIAMOND, 5));
		assertEquals(Material.DIAMOND, snowball.getItem().getType());
		assertEquals(1, snowball.getItem().getAmount());
	}

	@Test
	void testSetItemNull()
	{
		assertThrows(IllegalArgumentException.class, () -> snowball.setItem(null));
	}

}
