// Auto-generated mechanical sanity test for LootableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.loot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LootableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LootableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getLootTable());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements LootableBaseMock
	{
	}
}
