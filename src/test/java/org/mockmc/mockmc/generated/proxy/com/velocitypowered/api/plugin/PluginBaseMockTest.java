// Auto-generated mechanical sanity test for PluginBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

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
		assertNotNull(mock);
		assertSafeDefault(mock.name());
		assertSafeDefault(mock.version());
		assertSafeDefault(mock.url());
		assertSafeDefault(mock.id());
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.authors());
		assertSafeDefault(mock.dependencies());
	}
}
