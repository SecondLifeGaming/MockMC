// Auto-generated mechanical sanity test for BooleanDialogInputBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BooleanDialogInputBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BooleanDialogInputBaseMock mock = new Stub();
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
			assertSafeDefault(mock.onTrue());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.onFalse());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BooleanDialogInputBaseMock
	{
	}
}
