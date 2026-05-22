// Auto-generated mechanical sanity test for PluginProviderContextBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.bootstrap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginProviderContextBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginProviderContextBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLogger());
		assertSafeDefault(mock.getConfiguration());
		assertSafeDefault(mock.getDataDirectory());
		assertSafeDefault(mock.getPluginSource());
	}

	private static class Stub implements PluginProviderContextBaseMock
	{
	}
}
