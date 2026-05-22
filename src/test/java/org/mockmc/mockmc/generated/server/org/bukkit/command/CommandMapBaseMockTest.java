// Auto-generated mechanical sanity test for CommandMapBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandMapBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandMapBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKnownCommands());
	}

	private static class Stub implements CommandMapBaseMock
	{
	}
}
