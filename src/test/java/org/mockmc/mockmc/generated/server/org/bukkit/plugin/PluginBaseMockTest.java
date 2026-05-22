// Auto-generated mechanical sanity test for PluginBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class PluginBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getLogger());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getConfig());
		assertSafeDefault(mock.getDataFolder());
		assertSafeDefault(mock.getDataPath());
		assertSafeDefault(mock.getPluginLoader());
		assertSafeDefault(mock.getComponentLogger());
		assertSafeDefault(mock.getSLF4JLogger());
		assertSafeDefault(mock.getLog4JLogger());
		assertSafeDefault(mock.getLifecycleManager());
		assertSafeDefault(mock.getPluginMeta());
	}

	private static class Stub implements PluginBaseMock
	{
	}
}
