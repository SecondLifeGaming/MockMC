// Auto-generated mechanical sanity test for ChiseledBookshelfInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ChiseledBookshelfInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChiseledBookshelfInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHolder());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ChiseledBookshelfInventoryBaseMock
	{
	}
}
