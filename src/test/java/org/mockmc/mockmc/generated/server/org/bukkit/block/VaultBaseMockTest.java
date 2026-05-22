// Auto-generated mechanical sanity test for VaultBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VaultBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VaultBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLootTable());
		assertSafeDefault(mock.getKeyItem());
		assertSafeDefault(mock.getDisplayedLootTable());
		assertSafeDefault(mock.getRewardedPlayers());
		assertSafeDefault(mock.getConnectedPlayers());
		assertSafeDefault(mock.getDisplayedItem());
	}

	private static class Stub implements VaultBaseMock
	{
	}
}
