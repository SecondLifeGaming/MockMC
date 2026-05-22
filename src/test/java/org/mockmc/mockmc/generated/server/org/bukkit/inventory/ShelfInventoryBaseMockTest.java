// Auto-generated mechanical sanity test for ShelfInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ShelfInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ShelfInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHolder());
	}

	private static class Stub implements ShelfInventoryBaseMock
	{
	}
}
