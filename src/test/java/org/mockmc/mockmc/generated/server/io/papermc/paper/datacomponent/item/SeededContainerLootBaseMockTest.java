// Auto-generated mechanical sanity test for SeededContainerLootBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SeededContainerLootBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SeededContainerLootBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.lootTable());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SeededContainerLootBaseMock
	{
	}
}
