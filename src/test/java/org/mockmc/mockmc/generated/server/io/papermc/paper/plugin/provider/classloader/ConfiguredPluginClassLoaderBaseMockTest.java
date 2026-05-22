// Auto-generated mechanical sanity test for ConfiguredPluginClassLoaderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.provider.classloader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConfiguredPluginClassLoaderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConfiguredPluginClassLoaderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getGroup());
		assertSafeDefault(mock.getPlugin());
		assertSafeDefault(mock.getConfiguration());
	}

	private static class Stub implements ConfiguredPluginClassLoaderBaseMock
	{
	}
}
