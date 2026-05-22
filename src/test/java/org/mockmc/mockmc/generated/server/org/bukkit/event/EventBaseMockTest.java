// Auto-generated mechanical sanity test for EventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHandlers());
		assertSafeDefault(mock.getEventName());
	}

	private static class Stub implements EventBaseMock
	{
	}
}
