// Auto-generated mechanical sanity test for PermissionSubjectBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.permission;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PermissionSubjectBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PermissionSubjectBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPermissionChecker());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PermissionSubjectBaseMock
	{
	}
}
