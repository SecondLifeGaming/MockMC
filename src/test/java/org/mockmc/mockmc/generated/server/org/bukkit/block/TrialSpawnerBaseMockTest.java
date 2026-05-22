// Auto-generated mechanical sanity test for TrialSpawnerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TrialSpawnerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TrialSpawnerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTrackedPlayers());
		assertSafeDefault(mock.getTrackedEntities());
		assertSafeDefault(mock.getNormalConfiguration());
		assertSafeDefault(mock.getOminousConfiguration());
	}

	private static class Stub implements TrialSpawnerBaseMock
	{
	}
}
