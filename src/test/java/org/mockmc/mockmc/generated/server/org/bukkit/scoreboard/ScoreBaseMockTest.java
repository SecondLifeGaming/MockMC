// Auto-generated mechanical sanity test for ScoreBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class ScoreBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ScoreBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntry());
		assertSafeDefault(mock.numberFormat());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.customName());
		assertSafeDefault(mock.getScoreboard());
		assertSafeDefault(mock.getObjective());
	}

	private static class Stub implements ScoreBaseMock
	{
	}
}
