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
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getShortText());
	}

	private static class Stub implements HelpTopicBaseMock
	{
	}
}
