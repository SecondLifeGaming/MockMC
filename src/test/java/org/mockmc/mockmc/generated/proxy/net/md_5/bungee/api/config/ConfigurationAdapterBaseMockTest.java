// Auto-generated mechanical sanity test for ConfigurationAdapterBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConfigurationAdapterBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ConfigurationAdapterBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getListeners());
		assertSafeDefault(mock.getServers());
	}

	private static class Stub implements ConfigurationAdapterBaseMock
	{
	}
}
