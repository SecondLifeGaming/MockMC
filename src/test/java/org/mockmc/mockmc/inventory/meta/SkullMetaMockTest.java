package org.mockmc.mockmc.inventory.meta;

import org.bukkit.profile.PlayerProfile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.OfflinePlayerMock;
import org.mockmc.mockmc.entity.PlayerMock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class SkullMetaMockTest
{

	@MockMCInject
	private ServerMock server;

	@Test
	void testDefaultNoOwner()
	{
		SkullMetaMock meta = new SkullMetaMock();

		assertFalse(meta.hasOwner());
		assertNull(meta.getOwner());
		assertNull(meta.getOwningPlayer());
	}

	@Test
	void testSetOwner()
	{
		SkullMetaMock meta = new SkullMetaMock();

		assertTrue(meta.setOwner("TheBusyBiscuit"));
		assertTrue(meta.hasOwner());
		assertEquals("TheBusyBiscuit", meta.getOwner());
		assertEquals("TheBusyBiscuit", meta.getOwningPlayer().getName());
	}

	@Test
	void testSetOwningPlayer()
	{
		SkullMetaMock meta = new SkullMetaMock();

		assertTrue(meta.setOwningPlayer(new OfflinePlayerMock("TheBusyBiscuit")));
		assertTrue(meta.hasOwner());
		assertEquals("TheBusyBiscuit", meta.getOwner());
		assertEquals("TheBusyBiscuit", meta.getOwningPlayer().getName());
	}

	@Test
	void testNoPlayerProfile()
	{
		SkullMetaMock meta = new SkullMetaMock();

		assertNull(meta.getPlayerProfile());
	}

	@Test
	void testSetNullPlayerProfile()
	{
		SkullMetaMock meta = new SkullMetaMock();

		assertNull(meta.getPlayerProfile());
		meta.setPlayerProfile(null);
		assertNull(meta.getPlayerProfile());
	}

	@Test
	void testSetPlayerProfile()
	{
		SkullMetaMock meta = new SkullMetaMock();
		PlayerMock playerMock = server.addPlayer();

		meta.setPlayerProfile(playerMock.getPlayerProfile());
		assertEquals(playerMock.getPlayerProfile(), meta.getPlayerProfile());
	}

	@Test
	void testClone()
	{
		SkullMetaMock meta = new SkullMetaMock();

		assertTrue(meta.setOwner("TheBusyBiscuit"));

		SkullMetaMock clone = meta.clone();
		assertEquals(meta, clone);
		assertEquals("TheBusyBiscuit", clone.getOwner());
	}

	@Test
	void testSetOwnerProfile()
	{
		SkullMetaMock meta = new SkullMetaMock();
		PlayerMock playerMock = server.addPlayer();
		PlayerProfile profile = server.createPlayerProfile(UUID.randomUUID(), "Test");

		meta.setOwnerProfile(profile);

		assertNotNull(meta.getOwnerProfile());
		assertEquals(profile, meta.getOwnerProfile());
	}

}
