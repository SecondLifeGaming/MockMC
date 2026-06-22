// Auto-generated mechanical sanity test for TileStateBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class TileStateBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TileStateBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPersistentDataContainer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TileStateBaseMock
	{
	}
}
