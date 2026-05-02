// Auto-generated mechanical sanity test for PluginManagerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.plugin;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginManagerBaseMock mock = new PluginManagerBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getCommands());
		assertSafeDefault(mock.getPlugins());
	}
}
