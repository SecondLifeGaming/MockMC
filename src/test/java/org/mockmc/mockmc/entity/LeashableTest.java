package org.mockmc.mockmc.entity;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.world.WorldMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class LeashableTest
{

	@MockMCInject
	private ServerMock server;
	private PigMock pig;
	private Player player;

	@BeforeEach
	void setUp()
	{
		WorldMock world = server.addSimpleWorld("world");
		pig = world.spawn(world.getSpawnLocation(), PigMock.class);
		player = server.addPlayer();
	}

	@Test
	void isLeashed_Default_False()
	{
		assertFalse(pig.isLeashed());
	}

	@Test
	void getLeashHolder_NotLeashed_ThrowsException()
	{
		assertThrows(IllegalStateException.class, pig::getLeashHolder);
	}

	@Test
	void setLeashHolder_Player_Set()
	{
		assertTrue(pig.setLeashHolder(player));
		assertTrue(pig.isLeashed());
		assertEquals(player, pig.getLeashHolder());
	}

	@Test
	void setLeashHolder_Null_Unleashes()
	{
		pig.setLeashHolder(player);
		assertTrue(pig.isLeashed());

		assertTrue(pig.setLeashHolder(null));
		assertFalse(pig.isLeashed());
		assertThrows(IllegalStateException.class, pig::getLeashHolder);
	}

	@Test
	void setLeashHolder_DeadEntity_ReturnsFalse()
	{
		Player deadPlayer = server.addPlayer();
		deadPlayer.setHealth(0);
		assertTrue(deadPlayer.isDead());

		assertFalse(pig.setLeashHolder(deadPlayer));
		assertFalse(pig.isLeashed());
	}

}
