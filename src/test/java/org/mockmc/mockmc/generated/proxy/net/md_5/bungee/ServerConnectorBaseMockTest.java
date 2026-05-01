// Auto-generated mechanical sanity test for ServerConnectorBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerConnectorBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerConnectorBaseMock mock = new ServerConnectorBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getHandshakeHandler());
	}
}
