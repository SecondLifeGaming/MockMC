// Auto-generated mechanical sanity test for InventoryInteractEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class InventoryInteractEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		InventoryInteractEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getWhoClicked());
	}

	private static class Stub implements InventoryInteractEventBaseMock
	{
	}
}
