// Auto-generated mechanical sanity test for ProxyServerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyServerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyServerBaseMock mock = new ProxyServerBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.unsafe());
		assertSafeDefault(mock.getLogger());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getPluginsFolder());
		assertSafeDefault(mock.getPluginManager());
		assertSafeDefault(mock.getScheduler());
		assertSafeDefault(mock.getConfig());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getGameVersion());
		assertSafeDefault(mock.getServers());
		assertSafeDefault(mock.getChannels());
		assertSafeDefault(mock.getServersCopy());
		assertSafeDefault(mock.getConsole());
		assertSafeDefault(mock.getDisabledCommands());
		assertSafeDefault(mock.createTitle());
		assertSafeDefault(mock.getReconnectHandler());
		assertSafeDefault(mock.getConfigurationAdapter());
	}
}
