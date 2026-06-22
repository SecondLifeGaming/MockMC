// Auto-generated mechanical sanity test for ConversationCancellerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.conversations;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.conversations.ConversationCanceller;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ConversationCancellerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConversationCancellerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.clone());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ConversationCancellerBaseMock
	{
		@Override
		public ConversationCanceller clone()
		{
			return null;
		}
	}
}
