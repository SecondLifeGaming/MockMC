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
		try
		{
			assertSafeDefault(mock.getResult());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getWhoClicked());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements InventoryInteractEventBaseMock
	{
	}
}
