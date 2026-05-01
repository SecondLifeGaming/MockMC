// Auto-generated mechanical sanity test for ServerConnectRequestBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerConnectRequestBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerConnectRequestBaseMock mock = new ServerConnectRequestBaseMock()
		{
		};
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getReason());
		assertSafeDefault(mock.getCallback());
	}
}
