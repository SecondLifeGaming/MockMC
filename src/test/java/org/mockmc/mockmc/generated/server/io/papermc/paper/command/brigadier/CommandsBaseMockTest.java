// Auto-generated mechanical sanity test for CommandsBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDispatcher());
	}

	private static class Stub implements CommandsBaseMock
	{
	}
}
