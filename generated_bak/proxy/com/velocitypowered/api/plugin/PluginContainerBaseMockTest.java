// Auto-generated mechanical sanity test for PluginContainerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginContainerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginContainerBaseMock mock = new PluginContainerBaseMock()
		{
		};
		assertSafeDefault(mock.getInstance());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getExecutorService());
	}
}
