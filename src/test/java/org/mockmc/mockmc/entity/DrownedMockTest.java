package org.mockmc.mockmc.entity;

import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockMCExtension.class)
class DrownedMockTest
{

	@MockMCInject
	private DrownedMock drowned;

	@Test
	void hasCorrectParentClasses()
	{
		assertInstanceOf(Drowned.class, drowned);
		assertInstanceOf(ZombieMock.class, drowned);
		assertInstanceOf(MockRangedEntity.class, drowned);
	}

	@Test
	void getType()
	{
		assertEquals(EntityType.DROWNED, drowned.getType());
	}

	@Test
	void getEyeHeight_GivenDefaultDrowned()
	{
		assertEquals(1.74D, drowned.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenBabyDrowned()
	{
		drowned.setBaby();
		assertEquals(0.87D, drowned.getEyeHeight());
	}

}
