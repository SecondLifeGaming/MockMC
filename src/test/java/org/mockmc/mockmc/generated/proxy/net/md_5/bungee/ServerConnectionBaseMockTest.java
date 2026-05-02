// Auto-generated mechanical sanity test for ServerConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerConnectionBaseMock mock = new ServerConnectionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.unsafe());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getCh());
		assertSafeDefault(mock.getKeepAlives());
		assertSafeDefault(mock.getInfo());
		assertSafeDefault(mock.getSocketAddress());
	}
}
