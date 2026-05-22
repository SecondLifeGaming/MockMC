// Auto-generated mechanical sanity test for WorldBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class WorldBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WorldBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getGameRules());
		assertSafeDefault(mock.getWorldType());
		assertSafeDefault(mock.getEntities());
		assertSafeDefault(mock.getPluginChunkTickets());
		assertSafeDefault(mock.spigot());
		assertSafeDefault(mock.getLivingEntities());
		assertSafeDefault(mock.getFeatureFlags());
		assertSafeDefault(mock.audiences());
		assertSafeDefault(mock.getWorldPath());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getSpawnLocation());
		assertSafeDefault(mock.getGenerator());
		assertSafeDefault(mock.getBiomeProvider());
		assertSafeDefault(mock.getPopulators());
		assertSafeDefault(mock.getDifficulty());
		assertSafeDefault(mock.getWorldFolder());
		assertSafeDefault(mock.getWorldBorder());
		assertSafeDefault(mock.getInfiniburn());
		assertSafeDefault(mock.getRaids());
		assertSafeDefault(mock.getEnderDragonBattle());
	}

	@Test
	void testSafeDefaultsPart2()
	{
		WorldBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLoadedChunks());
		assertSafeDefault(mock.getForceLoadedChunks());
	}

	private static class Stub implements WorldBaseMock
	{
	}
}
