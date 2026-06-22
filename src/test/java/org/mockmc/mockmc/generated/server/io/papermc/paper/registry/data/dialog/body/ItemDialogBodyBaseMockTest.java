// Auto-generated mechanical sanity test for ItemDialogBodyBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.body;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ItemDialogBodyBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemDialogBodyBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.description());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.item());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ItemDialogBodyBaseMock
	{
	}
}
