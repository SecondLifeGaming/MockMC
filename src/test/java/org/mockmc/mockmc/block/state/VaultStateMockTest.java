package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockMCExtension.class)
class VaultStateMockTest
{

	@MockMCInject
	private ServerMock server;
	private VaultStateMock vault;

	@BeforeEach
	void setUp()
	{
		vault = new VaultStateMock(Material.VAULT);
	}

	@Test
	void testRewardedPlayers()
	{
		UUID uuid = UUID.randomUUID();
		assertFalse(vault.hasRewardedPlayer(uuid));
		vault.addRewardedPlayer(uuid);
		assertTrue(vault.hasRewardedPlayer(uuid));
		assertEquals(1, vault.getRewardedPlayers().size());

		vault.removeRewardedPlayer(uuid);
		assertFalse(vault.hasRewardedPlayer(uuid));
	}

	@Test
	void testKeyItem()
	{
		ItemStack item = new ItemStack(Material.TRIAL_KEY);
		vault.setKeyItem(item);
		assertEquals(item, vault.getKeyItem());
	}

	@Test
	void testRanges()
	{
		vault.setActivationRange(5.0);
		assertEquals(5.0, vault.getActivationRange());
		vault.setDeactivationRange(6.0);
		assertEquals(6.0, vault.getDeactivationRange());
	}

	@Test
	void testNextUpdateTime()
	{
		vault.setNextStateUpdateTime(100L);
		assertEquals(100L, vault.getNextStateUpdateTime());
	}
}
