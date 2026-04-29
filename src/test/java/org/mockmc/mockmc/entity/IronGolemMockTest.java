package org.mockmc.mockmc.entity;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockMCExtension.class)
class IronGolemMockTest
{

	@MockMCInject
	private IronGolemMock ironGolem;

	@Test
	void isPlayerCreated_GivenDefaultValue()
	{
		assertFalse(ironGolem.isPlayerCreated());
	}

	@ParameterizedTest
	@ValueSource(booleans =
	{true, false})
	void isPlayerCreated_GivenChangesInValue(boolean isPlayerCreated)
	{
		ironGolem.setPlayerCreated(isPlayerCreated);
		assertEquals(isPlayerCreated, ironGolem.isPlayerCreated());
	}

	@Test
	void getEyeHeight_GivenDefaultValue()
	{
		assertEquals(2.295D, ironGolem.getEyeHeight());
	}

	@Test
	void getDeathSound()
	{
		assertEquals(Sound.ENTITY_IRON_GOLEM_DEATH, ironGolem.getDeathSound());
	}

	@Test
	void getHurtSound()
	{
		assertEquals(Sound.ENTITY_IRON_GOLEM_HURT, ironGolem.getHurtSound());
	}

	@Test
	void getType()
	{
		assertEquals(EntityType.IRON_GOLEM, ironGolem.getType());
	}

}
