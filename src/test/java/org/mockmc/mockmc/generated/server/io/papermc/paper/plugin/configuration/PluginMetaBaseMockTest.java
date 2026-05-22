// Auto-generated mechanical sanity test for PluginMetaBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.configuration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getPermissions());
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.namespace());
		assertSafeDefault(mock.getAPIVersion());
		assertSafeDefault(mock.getWebsite());
		assertSafeDefault(mock.getAuthors());
		assertSafeDefault(mock.getContributors());
		assertSafeDefault(mock.getMainClass());
		assertSafeDefault(mock.getLoadOrder());
		assertSafeDefault(mock.getLoggerPrefix());
		assertSafeDefault(mock.getPluginDependencies());
		assertSafeDefault(mock.getPluginSoftDependencies());
		assertSafeDefault(mock.getLoadBeforePlugins());
		assertSafeDefault(mock.getProvidedPlugins());
		assertSafeDefault(mock.getPermissionDefault());
	}

	private static class Stub implements PluginMetaBaseMock
	{
	}
}
