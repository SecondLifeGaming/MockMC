// Auto-generated mechanical sanity test for PluginLoaderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class PluginLoaderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginLoaderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPluginFileFilters());
	}

	private static class Stub implements PluginLoaderBaseMock
	{
	}
}
