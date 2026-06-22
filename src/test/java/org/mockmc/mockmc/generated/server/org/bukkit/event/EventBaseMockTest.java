// Auto-generated mechanical sanity test for EventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class EventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EventBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHandlers());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getEventName());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EventBaseMock
	{
	}
}
