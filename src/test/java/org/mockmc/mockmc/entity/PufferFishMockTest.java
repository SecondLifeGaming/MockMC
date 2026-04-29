package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.entity.data.EntityState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockMCExtension.class)
class PufferFishMockTest
{

	@MockMCInject
	private PufferFishMock pufferFish;

	@Test
	void testGetBaseBucketItem()
	{
		assertEquals(Material.PUFFERFISH_BUCKET, pufferFish.getBaseBucketItem().getType());
	}

	@Test
	void testGetPuffStateDefault()
	{
		assertEquals(0, pufferFish.getPuffState());
	}

	@Test
	void testSetPuffState()
	{
		pufferFish.setPuffState(1);
		assertEquals(1, pufferFish.getPuffState());
	}

	@Test
	void testSetPuffStateToSmall()
	{
		assertThrows(IllegalArgumentException.class, () -> pufferFish.setPuffState(-1));
	}

	@Test
	void testSetPuffStateToBig()
	{
		assertThrows(IllegalArgumentException.class, () -> pufferFish.setPuffState(3));
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.PUFFERFISH, pufferFish.getType());
	}

	@Test
	void testGetEntityState()
	{
		pufferFish.setPuffState(0);
		assertEquals(EntityState.DEFAULT, pufferFish.getEntityState());
		pufferFish.setPuffState(1);
		assertEquals(EntityState.SEMI_PUFFED, pufferFish.getEntityState());
		pufferFish.setPuffState(2);
		assertEquals(EntityState.PUFFED, pufferFish.getEntityState());
	}

	@Test
	void getEyeHeight_GivenNormalPufferFish()
	{
		assertEquals(0.2275D, pufferFish.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenSemiPuffed()
	{
		pufferFish.setPuffState(1);
		assertEquals(0.3185D, pufferFish.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenPuffed()
	{
		pufferFish.setPuffState(2);
		assertEquals(0.455D, pufferFish.getEyeHeight());
	}

}
