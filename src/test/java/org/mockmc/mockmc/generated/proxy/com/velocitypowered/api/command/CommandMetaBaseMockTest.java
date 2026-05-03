// Auto-generated mechanical sanity test for CommandMetaBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		CommandMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPlugin());
		assertSafeDefault(mock.getAliases());
		assertSafeDefault(mock.getHints());
	}

	private static class Stub implements CommandMetaBaseMock
	{
	}
}
