package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import org.bukkit.Location;
import org.bukkit.entity.Warden;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		assertThrows(IllegalArgumentException.class, () -> warden.increaseAnger(player, 420));
	}

	@Test
	void testIncreaseAngerThrowsWithKnownEntityAndToBigAddedValue()
	{
		warden.setAnger(player, 140);
		assertThrows(IllegalArgumentException.class, () -> warden.increaseAnger(player, 20));
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

	@Test
	void testGetAngerTotal()
	{
		PlayerMock player2 = new PlayerMock(warden.getServer(), "Player2");
		warden.setAnger(player, 50);
		warden.setAnger(player2, 30);
		assertEquals(80, warden.getAnger());
	}

	@Test
	void testGetHighestAnger()
	{
		PlayerMock player2 = new PlayerMock(warden.getServer(), "Player2");
		warden.setAnger(player, 50);
		warden.setAnger(player2, 80);
		assertEquals(80, warden.getHighestAnger());
	}

	@Test
	void testGetEntityAngryAt()
	{
		PlayerMock player2 = new PlayerMock(warden.getServer(), "Player2");
		warden.setAnger(player, 50);
		warden.setAnger(player2, 80);
		assertEquals(player2, warden.getEntityAngryAt());
	}

	@Test
	void testClearAnger()
	{
		warden.setAnger(player, 50);
		warden.clearAnger(player);
		assertEquals(0, warden.getAnger(player));
	}

	@Test
	void testGetAngerLevel()
	{
		warden.setAnger(player, 30);
		assertEquals(Warden.AngerLevel.CALM, warden.getAngerLevel());
		warden.setAnger(player, 50);
		assertEquals(Warden.AngerLevel.AGITATED, warden.getAngerLevel());
		warden.setAnger(player, 100);
		assertEquals(Warden.AngerLevel.ANGRY, warden.getAngerLevel());
	}

	@Test
	void testDisturbanceLocation()
	{
		Location loc = new Location(null, 10, 20, 30);
		warden.setDisturbanceLocation(loc);
		assertEquals(loc, warden.getDisturbanceLocation());
	}

	@Test
	void testSonicBoomCooldown()
	{
		warden.setSonicBoomCooldown(40);
		assertEquals(40, warden.getSonicBoomCooldown());
	}

	@Test
	void testGetAngerPerEntity()
	{
		warden.setAnger(player, 50);
		assertTrue(warden.getAngerPerEntity().containsKey(player));
		assertEquals(50, warden.getAngerPerEntity().get(player));
	}

}
