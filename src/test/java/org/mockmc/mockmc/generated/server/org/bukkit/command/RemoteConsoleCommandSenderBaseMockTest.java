// Auto-generated mechanical sanity test for RemoteConsoleCommandSenderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RemoteConsoleCommandSenderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RemoteConsoleCommandSenderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAddress());
	}

	private static class Stub implements RemoteConsoleCommandSenderBaseMock
	{
	}
}
