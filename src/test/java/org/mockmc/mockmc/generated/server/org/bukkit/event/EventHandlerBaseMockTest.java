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
		try
		{
			assertSafeDefault(mock.priority());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EventHandlerBaseMock
	{
	}
}
