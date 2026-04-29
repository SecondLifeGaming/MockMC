package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class PigZombieMockTest
{

	@MockMCInject
	private PigZombieMock pigZombie;

	@Test
	void testAngerDefault()
	{
		assertEquals(0, pigZombie.getAnger());
	}

	@Test
	void testSetAnger()
	{
		pigZombie.setAnger(100);
		assertEquals(100, pigZombie.getAnger());
		assertTrue(pigZombie.isAngry());
	}

	@Test
	void testAngryDefault()
	{
		assertFalse(pigZombie.isAngry());
	}

	@Test
	void testSetAngry()
	{
		pigZombie.setAngry(true);
		assertTrue(pigZombie.isAngry());
	}

	@Test
	void testIsConverting()
	{
		assertFalse(pigZombie.isConverting());
	}

	@Test
	void testGetConversionTime()
	{
		assertThrows(UnsupportedOperationException.class, pigZombie::getConversionTime);
	}

	@Test
	void testSetConversionTime()
	{
		assertThrows(UnsupportedOperationException.class, () -> pigZombie.setConversionTime(100));
	}

	@Test
	void testType()
	{
		assertEquals(EntityType.ZOMBIFIED_PIGLIN, pigZombie.getType());
	}

}
