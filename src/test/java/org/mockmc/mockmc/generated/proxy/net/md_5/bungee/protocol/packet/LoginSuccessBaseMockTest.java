// Auto-generated mechanical sanity test for LoginSuccessBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LoginSuccessBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LoginSuccessBaseMock mock = new LoginSuccessBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getProperties());
		assertSafeDefault(mock.getUsername());
		assertSafeDefault(mock.getUuid());
	}
}
