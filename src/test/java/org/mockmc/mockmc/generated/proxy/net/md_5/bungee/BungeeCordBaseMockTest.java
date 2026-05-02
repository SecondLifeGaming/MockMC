// Auto-generated mechanical sanity test for BungeeCordBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class BungeeCordBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		BungeeCordBaseMock mock = new BungeeCordBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.unsafe());
		assertSafeDefault(mock.getLogger());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getPluginsFolder());
		assertSafeDefault(mock.getConnectionThrottle());
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
}
