// Auto-generated mechanical sanity test for PermissionManagerBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PermissionManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PermissionManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPermissions());
	}

	private static class Stub implements PermissionManagerBaseMock
	{
	}
}
