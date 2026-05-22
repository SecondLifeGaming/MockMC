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
		assertSafeDefault(mock.getHelpTopics());
		assertSafeDefault(mock.getIgnoredPlugins());
	}

	private static class Stub implements HelpMapBaseMock
	{
	}
}
