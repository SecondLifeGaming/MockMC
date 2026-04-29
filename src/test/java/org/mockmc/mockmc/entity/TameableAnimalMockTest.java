package org.mockmc.mockmc.entity;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.AnimalTamer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class TameableAnimalMockTest
{

	@MockMCInject
	private ServerMock server;
	@MockMCInject
	private TameableAnimalMock tameable;

	@Test
	void getOwner_givenNullOwner()
	{
		tameable.setOwner(null);

		AnimalTamer actual = tameable.getOwner();

		assertNull(actual);
		assertFalse(tameable.isTamed());
	}

	@Test
	void getOwner_givenOnlineOwner()
	{
		PlayerMock owner = server.addPlayer();
		tameable.setOwner(owner);

		AnimalTamer actual = tameable.getOwner();

		assertSame(owner, actual);
		assertTrue(tameable.isTamed());
	}

	@Test
	void getOwner_givenOfflineOwner()
	{
		server.setOfflinePlayers(1);
		OfflinePlayer owner = server.getOfflinePlayers()[0];
		tameable.setOwner(owner);

		AnimalTamer actual = tameable.getOwner();

		assertSame(owner, actual);
		assertTrue(tameable.isTamed());
	}

	@ParameterizedTest
	@ValueSource(booleans =
	{true, false})
	void setSitting_GivenEntityWithSittableInterface(boolean value)
	{
		WolfMock wolf = new WolfMock(server, UUID.randomUUID());
		wolf.setSitting(value);

		boolean actual = wolf.isSitting();

		assertEquals(value, actual);
	}

	@ParameterizedTest
	@ValueSource(booleans =
	{true, false})
	void setSitting_GivenEntityWithoutSittableInterface(boolean value)
	{
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> tameable.setSitting(value));
		assertEquals("Not sittable", e.getMessage());
	}

}
