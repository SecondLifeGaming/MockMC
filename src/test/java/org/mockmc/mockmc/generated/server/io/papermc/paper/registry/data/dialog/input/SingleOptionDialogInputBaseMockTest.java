// Auto-generated mechanical sanity test for SingleOptionDialogInputBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SingleOptionDialogInputBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SingleOptionDialogInputBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.entries());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.label());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SingleOptionDialogInputBaseMock
	{
	}
}
