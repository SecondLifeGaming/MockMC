// Auto-generated mechanical sanity test for ScoreboardBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class ScoreboardBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ScoreboardBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntries());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getObjectives());
		assertSafeDefault(mock.getTeams());
	}

	private static class Stub implements ScoreboardBaseMock
	{
	}
}
