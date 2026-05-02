// Auto-generated mechanical sanity test for LoginRequestBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LoginRequestBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LoginRequestBaseMock mock = new LoginRequestBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPublicKey());
		assertSafeDefault(mock.getData());
		assertSafeDefault(mock.getUuid());
	}
}
