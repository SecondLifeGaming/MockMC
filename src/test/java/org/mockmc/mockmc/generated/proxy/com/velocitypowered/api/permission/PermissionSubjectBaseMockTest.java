// Auto-generated mechanical sanity test for PermissionSubjectBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.permission;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PermissionSubjectBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PermissionSubjectBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPermissionChecker());
	}

	private static class Stub implements PermissionSubjectBaseMock
	{
	}
}
