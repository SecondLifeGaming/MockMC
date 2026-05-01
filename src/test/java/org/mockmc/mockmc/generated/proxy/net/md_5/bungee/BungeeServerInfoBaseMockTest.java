// Auto-generated mechanical sanity test for BungeeServerInfoBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class BungeeServerInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		BungeeServerInfoBaseMock mock = new BungeeServerInfoBaseMock()
		{
		};
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getPermission());
		assertSafeDefault(mock.getSocketAddress());
		assertSafeDefault(mock.getPacketQueue());
	}
}
