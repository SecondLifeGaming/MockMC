package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.inventory.ItemStackMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockMCExtension.class)
class ThrownExpBottleMockTest
{

	@MockMCInject
	private ThrownExpBottleMock bottle;

	@Test
	void testGetItem()
	{
		assertEquals(Material.EXPERIENCE_BOTTLE, bottle.getItem().getType());
	}

	@Test
	void testSetItem()
	{
		bottle.setItem(new ItemStackMock(Material.DIAMOND, 32));

		assertEquals(Material.DIAMOND, bottle.getItem().getType());
		assertEquals(1, bottle.getItem().getAmount());
	}

	@Test
	void testSetItemNull()
	{
		assertThrows(IllegalArgumentException.class, () -> bottle.setItem(null));
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.EXPERIENCE_BOTTLE, bottle.getType());
	}

}
