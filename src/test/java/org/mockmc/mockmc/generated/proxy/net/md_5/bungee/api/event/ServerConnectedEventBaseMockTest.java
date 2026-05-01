// Auto-generated mechanical sanity test for ServerConnectedEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerConnectedEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerConnectedEventBaseMock mock = new ServerConnectedEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getServer());
	}
}
