// Auto-generated mechanical sanity test for LoginPayloadRequestBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LoginPayloadRequestBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LoginPayloadRequestBaseMock mock = new LoginPayloadRequestBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getChannel());
		assertSafeDefault(mock.getData());
	}
}
