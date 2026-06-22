// Auto-generated mechanical sanity test for PluginLoaderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PluginLoaderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginLoaderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPluginFileFilters());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PluginLoaderBaseMock
	{
	}
}
