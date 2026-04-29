package org.mockmc.mockmc.entity;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class IllusionerMockTest
{

	@MockMCInject
	private IllusionerMock illusioner;

	@Test
	void getCelebrationSound()
	{
		assertEquals(Sound.ENTITY_ILLUSIONER_AMBIENT, illusioner.getCelebrationSound());
	}

	@Test
	void getEyeHeight_GivenDefaultValue()
	{
		assertEquals(1.6575D, illusioner.getEyeHeight());
	}

	@Test
	void getType()
	{
		assertEquals(EntityType.ILLUSIONER, illusioner.getType());
	}

}
