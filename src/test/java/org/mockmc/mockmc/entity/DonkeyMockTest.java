package org.mockmc.mockmc.entity;

import org.bukkit.entity.Horse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class DonkeyMockTest
{

	@MockMCInject
	private DonkeyMock donkey;

	@Test
	void testVariant()
	{
		assertEquals(Horse.Variant.DONKEY, donkey.getVariant());
	}

	@Test
	void getEyeHeight_GivenDefaultDonkey()
	{
		assertEquals(1.425D, donkey.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenBabyDonkey()
	{
		donkey.setBaby();
		assertEquals(0.7125D, donkey.getEyeHeight());
	}

}
