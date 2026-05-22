// Auto-generated mechanical sanity test for BeaconInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BeaconInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BeaconInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItem());
	}

	private static class Stub implements BeaconInventoryBaseMock
	{
	}
}
