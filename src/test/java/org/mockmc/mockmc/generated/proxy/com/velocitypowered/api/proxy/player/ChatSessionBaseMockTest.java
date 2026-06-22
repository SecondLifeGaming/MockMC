// Auto-generated mechanical sanity test for ChatSessionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ChatSessionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChatSessionBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSessionId());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ChatSessionBaseMock
	{
	}
}
