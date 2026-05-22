// Auto-generated mechanical sanity test for PlayerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class PlayerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.locale());
		assertSafeDefault(mock.identity());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.displayName());
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.getLocale());
		assertSafeDefault(mock.getResourcePackHash());
		assertSafeDefault(mock.spigot());
		assertSafeDefault(mock.getRespawnLocation());
		assertSafeDefault(mock.getPlayerProfile());
		assertSafeDefault(mock.getWorldBorder());
		assertSafeDefault(mock.getResourcePackStatus());
		assertSafeDefault(mock.activeBossBars());
		assertSafeDefault(mock.playerListName());
		assertSafeDefault(mock.playerListHeader());
		assertSafeDefault(mock.playerListFooter());
		assertSafeDefault(mock.getPlayerListName());
		assertSafeDefault(mock.getPlayerListHeader());
		assertSafeDefault(mock.getPlayerListFooter());
	}

	@Test
	void testSafeDefaultsPart2()
	{
		PlayerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCompassTarget());
		assertSafeDefault(mock.getHAProxyAddress());
		assertSafeDefault(mock.getEnderPearls());
		assertSafeDefault(mock.getCurrentInput());
		assertSafeDefault(mock.getPreviousGameMode());
		assertSafeDefault(mock.getPlayerWeather());
		assertSafeDefault(mock.hasFlyingFallDamage());
		assertSafeDefault(mock.getScoreboard());
		assertSafeDefault(mock.getSpectatorTarget());
		assertSafeDefault(mock.getClientBrandName());
		assertSafeDefault(mock.getIdleDuration());
		assertSafeDefault(mock.getSentChunkKeys());
		assertSafeDefault(mock.getSentChunks());
		assertSafeDefault(mock.getConnection());
	}

	private static class Stub implements PlayerBaseMock
	{
	}
}
