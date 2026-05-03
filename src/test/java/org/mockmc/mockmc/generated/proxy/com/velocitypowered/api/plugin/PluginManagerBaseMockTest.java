// Auto-generated mechanical sanity test for PluginManagerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PluginManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPlugins());
	}

	private static class Stub implements PluginManagerBaseMock
	{
	}
}
