package org.mockmc.mockmc.entity;

import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class DragonFireballMockTest
{

	@MockMCInject
	private DragonFireball dragonFireball;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.DRAGON_FIREBALL, dragonFireball.getType());
	}

}
