// Auto-generated mechanical sanity test for ScoreboardManagerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ScoreboardManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ScoreboardManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getNewScoreboard());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getMainScoreboard());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ScoreboardManagerBaseMock
	{
	}
}
