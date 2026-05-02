// Auto-generated mechanical sanity test for PermissionsSetupEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.permission;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PermissionsSetupEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PermissionsSetupEventBaseMock mock = new PermissionsSetupEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getProvider());
		assertSafeDefault(mock.getSubject());
	}
}
