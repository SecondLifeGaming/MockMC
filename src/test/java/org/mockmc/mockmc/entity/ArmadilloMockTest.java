package org.mockmc.mockmc.entity;

import org.bukkit.entity.Armadillo;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class ArmadilloMockTest
{

	@MockMCInject
	private Armadillo armadillo;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.ARMADILLO, armadillo.getType());
	}

}
