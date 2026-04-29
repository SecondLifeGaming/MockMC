package org.mockmc.mockmc.entity;

import org.bukkit.Sound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class FishMockTest
{

	@MockMCInject
	private PufferFishMock fish;

	@Test
	void testIsFromBucketDefault()
	{
		assertFalse(fish.isFromBucket());
	}

	@Test
	void testSetFromBucket()
	{
		fish.setFromBucket(true);
		assertTrue(fish.isFromBucket());
	}

	@Test
	void testGetPickupSound()
	{
		assertEquals(Sound.ITEM_BUCKET_FILL_FISH, fish.getPickupSound());
	}

}
