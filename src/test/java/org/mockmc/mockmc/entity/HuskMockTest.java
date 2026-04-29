package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class HuskMockTest
{

	@MockMCInject
	private HuskMock husk;

	@Test
	void getType()
	{
		assertEquals(EntityType.HUSK, husk.getType());
	}

	@Test
	void getHeight_GivenDefaultHusk()
	{
		assertEquals(1.95D, husk.getHeight());
	}

	@Test
	void getHeight_GivenBabyHusk()
	{
		husk.setBaby();
		assertEquals(0.975D, husk.getHeight());
	}

	@Test
	void getWidth_GivenDefaultHusk()
	{
		assertEquals(0.6D, husk.getWidth());
	}

	@Test
	void getWidth_GivenBabyHusk()
	{
		husk.setBaby();
		assertEquals(0.3D, husk.getWidth());
	}

	@Test
	void getEyeHeight_GivenDefaultHusk()
	{
		assertEquals(1.74D, husk.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenBabyHusk()
	{
		husk.setBaby();
		assertEquals(0.93D, husk.getEyeHeight());
	}

}
