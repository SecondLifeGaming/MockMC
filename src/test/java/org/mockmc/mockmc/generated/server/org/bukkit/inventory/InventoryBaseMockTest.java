// Auto-generated mechanical sanity test for InventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class InventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		InventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.iterator());
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getContents());
		assertSafeDefault(mock.getHolder());
		assertSafeDefault(mock.getViewers());
		assertSafeDefault(mock.getStorageContents());
	}

	private static class Stub implements InventoryBaseMock
	{
	}
}
