// Auto-generated mechanical sanity test for DragonBattleBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.boss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DragonBattleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DragonBattleBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBossBar());
		assertSafeDefault(mock.getEnderDragon());
		assertSafeDefault(mock.getEndPortalLocation());
		assertSafeDefault(mock.getRespawnPhase());
		assertSafeDefault(mock.getRespawnCrystals());
		assertSafeDefault(mock.getHealingCrystals());
	}

	private static class Stub implements DragonBattleBaseMock
	{
	}
}
