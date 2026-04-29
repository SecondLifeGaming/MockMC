package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockMCExtension.class)
class WardenMockTest
{

	@MockMCInject
	private WardenMock warden;
	@MockMCInject
	private PlayerMock player;

	@Test
	void testGetAngerDefault()
	{
		assertEquals(0, warden.getAnger(player));
	}

	@Test
	void testGetAngerNullEntityThrows()
	{
		assertThrows(NullPointerException.class, () -> warden.getAnger(null));
	}

	@Test
	void testSetAnger()
	{
		int anger = 42;
		warden.setAnger(player, anger);
		assertEquals(anger, warden.getAnger(player));
	}

	@Test
	void testSetAngerNullEntityThrows()
	{
		assertThrows(NullPointerException.class, () -> warden.setAnger(null, 50));
	}

	@Test
	void testSetAngerBiggerThan150Throws()
	{
		assertThrows(IllegalArgumentException.class, () -> warden.setAnger(player, 420));
	}

	@Test
	void testIncreaseAngerThrowsNullEntity()
	{
		assertThrows(NullPointerException.class, () -> warden.increaseAnger(null, 69));
	}

	@Test
	void testIncreaseAngerThrowsWithUnknownEntityAndToBigValue()
	{
		assertThrows(IllegalStateException.class, () -> warden.increaseAnger(player, 420));
	}

	@Test
	void testIncreaseAngerThrowsWithKnownEntityAndToBigAddedValue()
	{
		warden.setAnger(player, 140);
		assertThrows(IllegalStateException.class, () -> warden.increaseAnger(player, 20));
	}

	@Test
	void testIncreaseAngerWithUnknownPlayer()
	{
		warden.increaseAnger(player, 40);
		assertEquals(40, warden.getAnger(player));
	}

	@Test
	void testIncreaseAngerWithKnownEntity()
	{
		warden.setAnger(player, 29);
		warden.increaseAnger(player, 40);

		assertEquals(69, warden.getAnger(player));
	}

}
