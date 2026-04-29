package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMC;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class GuardianMockTest
{

	@MockMCInject
	private GuardianMock guardian;

	@Test
	void testHasLaserDefault()
	{
		assertFalse(guardian.hasLaser());
	}

	@Test
	void testSetLaserWithNullTargetThrows()
	{
		assertFalse(guardian.setLaser(true));
	}

	@Test
	void testSetLaser()
	{
		guardian.setTarget(new PlayerMock(MockMC.getMock(), "player"));
		assertTrue(guardian.setLaser(true));
	}

	@Test
	void testIsElderDefault()
	{
		assertFalse(guardian.isElder());
	}

	@Test
	void testSetElder()
	{
		assertThrows(UnsupportedOperationException.class, () -> guardian.setElder(true));
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.GUARDIAN, guardian.getType());
	}

}
