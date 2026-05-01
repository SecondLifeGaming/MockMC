// Auto-generated mechanical sanity test for ScoreboardBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.score;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ScoreboardBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ScoreboardBaseMock mock = new ScoreboardBaseMock()
		{
		};
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPosition());
		assertSafeDefault(mock.getObjectives());
		assertSafeDefault(mock.getScores());
		assertSafeDefault(mock.getTeams());
	}
}
