// Auto-generated mechanical sanity test for PluginMessageRecipientBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin.messaging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PluginMessageRecipientBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginMessageRecipientBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getListeningPluginChannels());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PluginMessageRecipientBaseMock
	{
	}
}
