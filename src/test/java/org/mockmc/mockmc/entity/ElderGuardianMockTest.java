package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.bukkit.entity.EntityType.ELDER_GUARDIAN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class ElderGuardianMockTest
{

	@MockMCInject
	private ElderGuardianMock guardian;

	@Test
	void testGetType()
	{
		assertEquals(ELDER_GUARDIAN, guardian.getType());
	}

	@Test
	void testIsElder()
	{
		assertTrue(guardian.isElder());
	}

}
