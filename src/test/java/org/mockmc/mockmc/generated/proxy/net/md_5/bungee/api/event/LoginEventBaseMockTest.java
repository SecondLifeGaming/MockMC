// Auto-generated mechanical sanity test for LoginEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

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
		assertSafeDefault(mock.getReason());
		assertSafeDefault(mock.getConnection());
		assertSafeDefault(mock.getLoginResult());
		assertSafeDefault(mock.getCancelReason());
		assertSafeDefault(mock.getCancelReasonComponents());
	}
}
