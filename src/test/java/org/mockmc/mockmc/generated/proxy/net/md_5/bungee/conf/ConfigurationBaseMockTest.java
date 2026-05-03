// Auto-generated mechanical sanity test for ConfigurationBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.conf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ConfigurationBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getListeners());
		assertSafeDefault(mock.getUuid());
		assertSafeDefault(mock.getServers());
		assertSafeDefault(mock.getFavicon());
		assertSafeDefault(mock.getServersCopy());
		assertSafeDefault(mock.getDisabledCommands());
		assertSafeDefault(mock.getFaviconObject());
		assertSafeDefault(mock.getServersLock());
	}

	private static class Stub implements ConfigurationBaseMock
	{
	}
}
