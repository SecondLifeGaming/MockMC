// Auto-generated mechanical sanity test for PluginEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PluginEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginEventBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPlugin());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PluginEventBaseMock
	{
	}
}
