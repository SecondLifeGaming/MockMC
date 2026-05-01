// Auto-generated mechanical sanity test for ServerPingBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerPingBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerPingBaseMock mock = new ServerPingBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getFavicon());
		assertSafeDefault(mock.getDescriptionComponent());
		assertSafeDefault(mock.getModinfo());
		assertSafeDefault(mock.getFaviconObject());
	}
}
