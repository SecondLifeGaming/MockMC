// Auto-generated mechanical sanity test for MessengerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin.messaging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MessengerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MessengerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOutgoingChannels());
		assertSafeDefault(mock.getIncomingChannels());
	}

	private static class Stub implements MessengerBaseMock
	{
	}
}
