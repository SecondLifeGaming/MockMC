// Auto-generated mechanical sanity test for PluginManagerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

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
		assertSafeDefault(mock.getPlugins());
	}
}
