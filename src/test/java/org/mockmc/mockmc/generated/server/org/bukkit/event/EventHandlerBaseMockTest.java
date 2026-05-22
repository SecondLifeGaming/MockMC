// Auto-generated mechanical sanity test for EventHandlerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EventHandlerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EventHandlerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.priority());
	}

	private static class Stub implements EventHandlerBaseMock
	{
	}
}
