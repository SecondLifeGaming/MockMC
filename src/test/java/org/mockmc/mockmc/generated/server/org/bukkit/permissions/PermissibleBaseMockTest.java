// Auto-generated mechanical sanity test for PermissibleBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.permissions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PermissibleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PermissibleBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getEffectivePermissions());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PermissibleBaseMock
	{
	}
}
