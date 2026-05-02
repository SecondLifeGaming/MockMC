// Auto-generated mechanical sanity test for ClientConnectEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ClientConnectEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ClientConnectEventBaseMock mock = new ClientConnectEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getListener());
		assertSafeDefault(mock.getSocketAddress());
	}
}
