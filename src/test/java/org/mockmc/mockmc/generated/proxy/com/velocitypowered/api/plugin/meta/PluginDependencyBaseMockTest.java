// Auto-generated mechanical sanity test for PluginDependencyBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin.meta;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginDependencyBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginDependencyBaseMock mock = new PluginDependencyBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getVersion());
	}
}
