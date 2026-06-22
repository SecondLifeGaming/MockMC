// Auto-generated mechanical sanity test for ItemLoreBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ItemLoreBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemLoreBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.lines());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.styledLines());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ItemLoreBaseMock
	{
	}
}
