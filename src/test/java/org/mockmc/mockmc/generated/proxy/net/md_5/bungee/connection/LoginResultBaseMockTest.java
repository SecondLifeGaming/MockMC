// Auto-generated mechanical sanity test for LoginResultBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LoginResultBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LoginResultBaseMock mock = new LoginResultBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getProperties());
		assertSafeDefault(mock.getId());
	}
}
