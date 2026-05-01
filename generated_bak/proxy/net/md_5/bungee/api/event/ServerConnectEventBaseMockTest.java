// Auto-generated mechanical sanity test for ServerConnectEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerConnectEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerConnectEventBaseMock mock = new ServerConnectEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getReason());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getRequest());
	}
}
