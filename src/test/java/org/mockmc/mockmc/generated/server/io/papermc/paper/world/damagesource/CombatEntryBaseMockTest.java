// Auto-generated mechanical sanity test for CombatEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.world.damagesource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CombatEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CombatEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDamageSource());
		assertSafeDefault(mock.getFallLocationType());
	}

	private static class Stub implements CombatEntryBaseMock
	{
	}
}
