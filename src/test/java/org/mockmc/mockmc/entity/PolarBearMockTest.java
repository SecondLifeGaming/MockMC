package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class PolarBearMockTest
{

	@MockMCInject
	private PolarBearMock polarBear;

	@Test
	void testIsStandingDefault()
	{
		assertFalse(polarBear.isStanding());
	}

	@Test
	void testSetStanding()
	{
		polarBear.setStanding(true);
		assertTrue(polarBear.isStanding());
	}

	@Test
	void testCanBreed()
	{
		assertFalse(polarBear.canBreed());
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.POLAR_BEAR, polarBear.getType());
	}

	@Test
	void getEyeHeight_GivenDefaultPolarBear()
	{
		assertEquals(1.19D, polarBear.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenBabyPolarBear()
	{
		polarBear.setBaby();
		assertEquals(0.595D, polarBear.getEyeHeight());
	}

}
