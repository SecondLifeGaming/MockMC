// Auto-generated mechanical sanity test for CommandBlockHolderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandBlockHolderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandBlockHolderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCommand());
		assertSafeDefault(mock.lastOutput());
	}

	private static class Stub implements CommandBlockHolderBaseMock
	{
	}
}
