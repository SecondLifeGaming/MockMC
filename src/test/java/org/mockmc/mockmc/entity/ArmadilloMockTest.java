package org.mockmc.mockmc.entity;

import org.bukkit.entity.Armadillo;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class ArmadilloMockTest
{

	@MockMCInject
	private ServerMock server;
	private ArmadilloMock armadillo;

	@BeforeEach
	void setUp()
	{
		armadillo = new ArmadilloMock(server, UUID.randomUUID());
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.ARMADILLO, armadillo.getType());
	}

	@Test
	void testStateDefault()
	{
		assertEquals(Armadillo.State.IDLE, armadillo.getState());
	}

	@Test
	void testRollUp()
	{
		armadillo.rollUp();
		assertEquals(Armadillo.State.SCARED, armadillo.getState());
	}

	@Test
	void testRollOut()
	{
		armadillo.rollUp();
		armadillo.rollOut();
		assertEquals(Armadillo.State.IDLE, armadillo.getState());
	}

	@Test
	void testSetState()
	{
		armadillo.setState(Armadillo.State.ROLLING);
		assertEquals(Armadillo.State.ROLLING, armadillo.getState());
	}
}
