// Auto-generated mechanical sanity test for ChestBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChestBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChestBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBlockInventory());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ChestBaseMock
	{
	}
}
