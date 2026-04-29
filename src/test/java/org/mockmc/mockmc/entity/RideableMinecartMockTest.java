package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.RideableMinecart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class RideableMinecartMockTest
{

	@MockMCInject
	private RideableMinecart minecart;

	@Test
	void testGetMinecartMaterial()
	{
		assertEquals(Material.MINECART, minecart.getMinecartMaterial());
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.MINECART, minecart.getType());
	}

}
