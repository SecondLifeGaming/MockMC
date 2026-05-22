// Auto-generated mechanical sanity test for TeamBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class TeamBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TeamBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.prefix());
		assertSafeDefault(mock.suffix());
		assertSafeDefault(mock.getEntries());
		assertSafeDefault(mock.color());
		assertSafeDefault(mock.displayName());
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getPrefix());
		assertSafeDefault(mock.getScoreboard());
		assertSafeDefault(mock.getSuffix());
		assertSafeDefault(mock.getNameTagVisibility());
	}

	private static class Stub implements TeamBaseMock
	{
	}
}
