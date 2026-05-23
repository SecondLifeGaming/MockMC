// Auto-generated mechanical sanity test for OfflinePlayerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class OfflinePlayerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OfflinePlayerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getPersistentDataContainer());
		assertSafeDefault(mock.getBedSpawnLocation());
		assertSafeDefault(mock.getLastDeathLocation());
		assertSafeDefault(mock.getRespawnLocation());
		assertSafeDefault(mock.getPlayerProfile());
		assertSafeDefault(mock.getUniqueId());
	}

	private static class Stub implements OfflinePlayerBaseMock
	{
	}
}
