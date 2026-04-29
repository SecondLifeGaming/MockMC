package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockMCExtension.class)
class GolemMockTest
{

	@MockMCInject
	private GolemMock golem;

	@Test
	void getAmbientSound()
	{
		assertNull(golem.getAmbientSound());
	}

	@Test
	void getHurtSound()
	{
		assertNull(golem.getHurtSound());
	}

	@Test
	void getDeathSound()
	{
		assertNull(golem.getDeathSound());
	}

}
