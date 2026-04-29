package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockMCExtension.class)
class ArrowMockTest
{

	@MockMCInject
	private ArrowMock arrow;

	@Test
	void getType()
	{
		assertEquals(EntityType.ARROW, arrow.getType());
	}

	@Test
	void getDamage_default()
	{
		assertEquals(6.0, arrow.getDamage());
	}

	@Test
	void testBasePotionType()
	{
		assertNull(arrow.getBasePotionType());

		arrow.setBasePotionType(PotionType.HEALING);
		assertEquals(PotionType.HEALING, arrow.getBasePotionType());

		arrow.setBasePotionType(null);
		assertNull(arrow.getBasePotionType());
	}

}
