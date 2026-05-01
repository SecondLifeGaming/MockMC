// Auto-generated mechanical sanity test for ServerDataBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerDataBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerDataBaseMock mock = new ServerDataBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getIcon());
	}
}
