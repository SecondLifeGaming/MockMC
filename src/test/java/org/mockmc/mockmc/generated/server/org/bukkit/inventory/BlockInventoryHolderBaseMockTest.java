// Auto-generated mechanical sanity test for BlockInventoryHolderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockInventoryHolderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockInventoryHolderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBlock());
	}

	private static class Stub implements BlockInventoryHolderBaseMock
	{
	}
}
