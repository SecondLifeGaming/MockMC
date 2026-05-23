// Auto-generated mechanical sanity test for PluginClassLoaderGroupBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.provider.classloader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginClassLoaderGroupBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginClassLoaderGroupBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAccess());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PluginClassLoaderGroupBaseMock
	{
	}
}
