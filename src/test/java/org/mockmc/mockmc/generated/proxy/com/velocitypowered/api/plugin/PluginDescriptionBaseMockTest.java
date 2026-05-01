// Auto-generated mechanical sanity test for PluginDescriptionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginDescriptionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginDescriptionBaseMock mock = new PluginDescriptionBaseMock()
		{
		};
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getSource());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getUrl());
		assertSafeDefault(mock.getAuthors());
		assertSafeDefault(mock.getDependencies());
	}
}
