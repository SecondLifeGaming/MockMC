// Auto-generated mechanical sanity test for CombatTrackerBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.world.damagesource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CombatTrackerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CombatTrackerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntity());
		assertSafeDefault(mock.getDeathMessage());
		assertSafeDefault(mock.calculateFallLocationType());
		assertSafeDefault(mock.getCombatEntries());
		assertSafeDefault(mock.computeMostSignificantFall());
	}

	private static class Stub implements CombatTrackerBaseMock
	{
	}
}
