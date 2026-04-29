package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class BlazeMockTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private BlazeMock blaze;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.BLAZE, blaze.getType());
	}

}
