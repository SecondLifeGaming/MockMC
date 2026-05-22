// Auto-generated mechanical sanity test for PluginManagerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPermissions());
		assertSafeDefault(mock.getPlugins());
	}

	private static class Stub implements PluginManagerBaseMock
	{
	}
}
