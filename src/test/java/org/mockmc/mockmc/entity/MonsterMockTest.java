package org.mockmc.mockmc.entity;

import org.bukkit.entity.SpawnCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class MonsterMockTest
{

	@MockMCInject
	private ZombieMock monster;

	@Test
	void testGetSpawnCategory()
	{
		assertEquals(SpawnCategory.MONSTER, monster.getSpawnCategory());
	}

}
