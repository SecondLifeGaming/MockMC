package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class RabbitMockTest
{

	@MockMCInject
	private RabbitMock rabbit;

	@Test
	void testGetRabbitType()
	{
		assertEquals(RabbitMock.Type.BLACK, rabbit.getRabbitType());
	}

	@Test
	void testSetRabbitType()
	{
		rabbit.setRabbitType(RabbitMock.Type.BLACK_AND_WHITE);
		assertEquals(RabbitMock.Type.BLACK_AND_WHITE, rabbit.getRabbitType());
	}

	@Test
	void testGetMoreCarrotTicks()
	{
		assertEquals(0, rabbit.getMoreCarrotTicks());
	}

	@Test
	void testSetMoreCarrotTicks()
	{
		rabbit.setMoreCarrotTicks(42);
		assertEquals(42, rabbit.getMoreCarrotTicks());
	}

	@Test
	void testEntityType()
	{
		assertEquals(EntityType.RABBIT, rabbit.getType());
	}

	@Test
	void getEyeHeight_GivenDefaultRabbit()
	{
		assertEquals(0.425D, rabbit.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenBabyRabbit()
	{
		rabbit.setBaby();
		assertEquals(0.2125D, rabbit.getEyeHeight());
	}

}
