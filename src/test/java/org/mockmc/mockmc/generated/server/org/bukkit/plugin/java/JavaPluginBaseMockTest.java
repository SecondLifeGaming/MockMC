// Auto-generated mechanical sanity test for JavaPluginBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class JavaPluginBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JavaPluginBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getLogger());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getConfig());
		assertSafeDefault(mock.getDataFolder());
		assertSafeDefault(mock.getPluginLoader());
		assertSafeDefault(mock.getLifecycleManager());
		assertSafeDefault(mock.getPluginMeta());
	}

	private static class Stub implements JavaPluginBaseMock
	{
	}
}
