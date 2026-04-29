package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockMCExtension.class)
class FrogMockTest
{

	@MockMCInject
	private FrogMock frog;

	@Test
	void testGetTongueTargetDefault()
	{
		assertNull(frog.getTongueTarget());
	}

	@Test
	void testSetTongueTarget(@MockMCInject PlayerMock playerMock)
	{
		frog.setTongueTarget(playerMock);
		assertEquals(playerMock, frog.getTongueTarget());
	}

	@Test
	void testGetVariantDefault()
	{
		assertEquals(Frog.Variant.TEMPERATE, frog.getVariant());
	}

	@Test
	void testSetVariant()
	{
		frog.setVariant(Frog.Variant.COLD);
		assertEquals(Frog.Variant.COLD, frog.getVariant());
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.FROG, frog.getType());
	}

}
