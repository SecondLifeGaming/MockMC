// Auto-generated mechanical sanity test for BeaconBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BeaconBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BeaconBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntitiesInRange());
		assertSafeDefault(mock.getPrimaryEffect());
		assertSafeDefault(mock.getSecondaryEffect());
	}

	private static class Stub implements BeaconBaseMock
	{
	}
}
