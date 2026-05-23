// Auto-generated mechanical sanity test for HelpTopicBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.help;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HelpTopicBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HelpTopicBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getName());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getShortText());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements HelpTopicBaseMock
	{
	}
}
