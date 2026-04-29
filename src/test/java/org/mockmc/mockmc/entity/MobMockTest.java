package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockMCExtension.class)
class MobMockTest
{

	@MockMCInject
	private MobMock mob;
	@MockMCInject
	private ServerMock server;

	@Test
	void testGetTargetDefault()
	{
		assertNull(mob.getTarget());
	}

	@Test
	void testSetTarget()
	{
		PlayerMock player = server.addPlayer();
		mob.setTarget(player);
		assertEquals(player, mob.getTarget());
	}

}
