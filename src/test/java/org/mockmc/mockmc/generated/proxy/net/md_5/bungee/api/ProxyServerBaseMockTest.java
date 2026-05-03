// Auto-generated mechanical sanity test for ProxyServerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ProxyServerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ProxyServerBaseMock mock = new Stub();
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
		assertSafeDefault(mock.getChannels());
		assertSafeDefault(mock.getServers());
		assertSafeDefault(mock.getServersCopy());
		assertSafeDefault(mock.getConsole());
		assertSafeDefault(mock.getDisabledCommands());
		assertSafeDefault(mock.createTitle());
		assertSafeDefault(mock.getReconnectHandler());
		assertSafeDefault(mock.getConfigurationAdapter());
	}

	private static class Stub implements ProxyServerBaseMock
	{
	}
}
