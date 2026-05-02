// Auto-generated mechanical sanity test for PermissionSubjectBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.permission;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PermissionSubjectBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PermissionSubjectBaseMock mock = new PermissionSubjectBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getPermissionChecker());
	}
}
