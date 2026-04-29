package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class MagmaCubeMockTest
{

	@MockMCInject
	private MagmaCube magmaCubeMock;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.MAGMA_CUBE, magmaCubeMock.getType());
	}

}
