// Auto-generated mechanical sanity test for DolphinBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DolphinBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DolphinBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getTreasureLocation());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DolphinBaseMock
	{
	}
}
