// Auto-generated mechanical sanity test for ProxyServerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyServerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		ProxyServerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getPluginManager());
		assertSafeDefault(mock.getScheduler());
		assertSafeDefault(mock.getConfiguration());
		assertSafeDefault(mock.getAllPlayers());
		assertSafeDefault(mock.getAllServers());
		assertSafeDefault(mock.getConsoleCommandSource());
		assertSafeDefault(mock.getEventManager());
		assertSafeDefault(mock.getCommandManager());
		assertSafeDefault(mock.getChannelRegistrar());
		assertSafeDefault(mock.getBoundAddress());
	}

	private static class Stub implements ProxyServerBaseMock
	{
	}
}
