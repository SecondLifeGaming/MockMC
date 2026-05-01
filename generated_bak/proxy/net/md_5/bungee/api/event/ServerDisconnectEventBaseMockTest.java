// Auto-generated mechanical sanity test for ServerDisconnectEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerDisconnectEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerDisconnectEventBaseMock mock = new ServerDisconnectEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getPlayer());
	}
}
