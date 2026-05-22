// Auto-generated mechanical sanity test for BeaconViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BeaconViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BeaconViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPrimaryEffect());
		assertSafeDefault(mock.getSecondaryEffect());
		assertSafeDefault(mock.getTopInventory());
	}

	private static class Stub implements BeaconViewBaseMock
	{
	}
}
