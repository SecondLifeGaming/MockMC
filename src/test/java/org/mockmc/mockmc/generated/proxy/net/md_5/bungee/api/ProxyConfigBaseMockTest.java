// Auto-generated mechanical sanity test for ProxyConfigBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyConfigBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyConfigBaseMock mock = new ProxyConfigBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getListeners());
		assertSafeDefault(mock.getGameVersion());
		assertSafeDefault(mock.getUuid());
		assertSafeDefault(mock.getServers());
		assertSafeDefault(mock.getFavicon());
		assertSafeDefault(mock.getServersCopy());
		assertSafeDefault(mock.getDisabledCommands());
		assertSafeDefault(mock.getFaviconObject());
	}
}
