// Auto-generated mechanical sanity test for MessageCommandSenderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class MessageCommandSenderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MessageCommandSenderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.name());
		assertSafeDefault(mock.spigot());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getEffectivePermissions());
	}

	private static class Stub implements MessageCommandSenderBaseMock
	{
	}
}
