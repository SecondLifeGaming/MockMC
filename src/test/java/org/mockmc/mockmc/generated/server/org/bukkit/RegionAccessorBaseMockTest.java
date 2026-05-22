// Auto-generated mechanical sanity test for RegionAccessorBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegionAccessorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegionAccessorBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.getEntities());
		assertSafeDefault(mock.getLivingEntities());
		assertSafeDefault(mock.getMoonPhase());
	}

	private static class Stub implements RegionAccessorBaseMock
	{
	}
}
