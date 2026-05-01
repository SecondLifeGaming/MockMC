// Auto-generated mechanical sanity test for PluginBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.plugin;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginBaseMock mock = new PluginBaseMock()
		{
		};
		assertSafeDefault(mock.getLogger());
		assertSafeDefault(mock.getFile());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getDataFolder());
		assertSafeDefault(mock.getSLF4JLogger());
		assertSafeDefault(mock.getExecutorService());
		assertSafeDefault(mock.getProxy());
	}
}
