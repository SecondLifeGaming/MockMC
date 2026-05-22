// Auto-generated mechanical sanity test for InventoryViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class InventoryViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		InventoryViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getTitle());
		assertSafeDefault(mock.getTopInventory());
		assertSafeDefault(mock.getCursor());
		assertSafeDefault(mock.getMenuType());
		assertSafeDefault(mock.getBottomInventory());
		assertSafeDefault(mock.getOriginalTitle());
	}

	private static class Stub implements InventoryViewBaseMock
	{
	}
}
