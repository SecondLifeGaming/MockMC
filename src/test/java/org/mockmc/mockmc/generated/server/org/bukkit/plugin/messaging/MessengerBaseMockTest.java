// Auto-generated mechanical sanity test for MessengerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin.messaging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class MessengerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MessengerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getOutgoingChannels());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getIncomingChannels());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements MessengerBaseMock
	{
	}
}
