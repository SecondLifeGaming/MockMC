package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.entity.data.EntitySubType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockMCExtension.class)
class ZombieVillagerMockTest
{

	@MockMCInject
	private ZombieVillagerMock zombie;

	@Test
	void getType()
	{
		assertEquals(EntityType.ZOMBIE_VILLAGER, zombie.getType());
	}

	@Test
	void getSubType_GivenDefaultZombie()
	{
		assertEquals(EntitySubType.DEFAULT, zombie.getSubType());
	}

	@Test
	void getVillagerType()
	{
		assertEquals(Villager.Type.PLAINS, zombie.getVillagerType());
	}

	@Test
	void setVillagerType()
	{
		zombie.setVillagerType(Villager.Type.JUNGLE);
		assertEquals(Villager.Type.JUNGLE, zombie.getVillagerType());
	}

	@Test
	void getConversionPlayer()
	{
		assertNull(zombie.getConversionPlayer());
	}

	@Test
	void getVillagerProfession()
	{
		assertEquals(Villager.Profession.NONE, zombie.getVillagerProfession());
	}

	@Test
	void setVillagerProfessions()
	{
		zombie.setVillagerProfession(Villager.Profession.BUTCHER);
		assertEquals(Villager.Profession.BUTCHER, zombie.getVillagerProfession());
	}

}
