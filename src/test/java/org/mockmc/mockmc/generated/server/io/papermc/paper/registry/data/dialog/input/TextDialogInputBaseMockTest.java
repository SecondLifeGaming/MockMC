// Auto-generated mechanical sanity test for TextDialogInputBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TextDialogInputBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TextDialogInputBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.label());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.multiline());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.initial());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TextDialogInputBaseMock
	{
	}
}
