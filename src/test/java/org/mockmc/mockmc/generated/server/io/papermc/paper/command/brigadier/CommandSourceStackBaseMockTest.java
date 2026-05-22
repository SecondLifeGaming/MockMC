// Auto-generated mechanical sanity test for CommandSourceStackBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandSourceStackBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandSourceStackBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getExecutor());
		assertSafeDefault(mock.getSender());
	}

	private static class Stub implements CommandSourceStackBaseMock
	{
	}
}
