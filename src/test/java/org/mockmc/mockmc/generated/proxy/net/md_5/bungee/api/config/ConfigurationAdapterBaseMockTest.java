// Auto-generated mechanical sanity test for ConfigurationAdapterBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.config;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConfigurationAdapterBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ConfigurationAdapterBaseMock mock = new ConfigurationAdapterBaseMock()
		{
		};
		assertSafeDefault(mock.getServers());
		assertSafeDefault(mock.getListeners());
	}
}
