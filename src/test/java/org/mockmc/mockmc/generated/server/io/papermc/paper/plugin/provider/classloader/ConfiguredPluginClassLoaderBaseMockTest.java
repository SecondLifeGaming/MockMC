// Auto-generated mechanical sanity test for ConfiguredPluginClassLoaderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.provider.classloader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ConfiguredPluginClassLoaderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConfiguredPluginClassLoaderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getConfiguration());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getPlugin());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getGroup());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ConfiguredPluginClassLoaderBaseMock
	{
	}
}
