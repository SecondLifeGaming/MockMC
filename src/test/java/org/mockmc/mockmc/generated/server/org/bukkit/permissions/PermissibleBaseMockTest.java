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
		assertSafeDefault(mock.getEffectivePermissions());
	}

	private static class Stub implements PermissibleBaseMock
	{
	}
}
