// Auto-generated mechanical sanity test for ChiseledBookshelfBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChiseledBookshelfBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChiseledBookshelfBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getSnapshotInventory());
	}

	private static class Stub implements ChiseledBookshelfBaseMock
	{
	}
}
