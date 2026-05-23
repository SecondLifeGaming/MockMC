// Auto-generated mechanical sanity test for HelpMapBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.help;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HelpMapBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HelpMapBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHelpTopics());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getIgnoredPlugins());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements HelpMapBaseMock
	{
	}
}
