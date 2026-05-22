// Auto-generated mechanical sanity test for ObjectiveBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class ObjectiveBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ObjectiveBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.displayName());
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.numberFormat());
		assertSafeDefault(mock.getCriteria());
		assertSafeDefault(mock.getScoreboard());
		assertSafeDefault(mock.getTrackedCriteria());
		assertSafeDefault(mock.getDisplaySlot());
		assertSafeDefault(mock.getRenderType());
	}

	private static class Stub implements ObjectiveBaseMock
	{
	}
}
