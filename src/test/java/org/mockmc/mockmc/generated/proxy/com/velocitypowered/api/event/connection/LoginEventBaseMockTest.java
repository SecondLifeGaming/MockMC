// Auto-generated mechanical sanity test for LoginEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LoginEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LoginEventBaseMock mock = new LoginEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getServerIdHash());
	}
}
