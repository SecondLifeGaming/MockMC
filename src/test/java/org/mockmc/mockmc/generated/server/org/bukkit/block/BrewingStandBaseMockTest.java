// Auto-generated mechanical sanity test for BrewingStandBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BrewingStandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrewingStandBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getSnapshotInventory());
	}

	private static class Stub implements BrewingStandBaseMock
	{
	}
}
