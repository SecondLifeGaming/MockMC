// Auto-generated mechanical sanity test for CommandManagerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		CommandManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAliases());
	}

	private static class Stub implements CommandManagerBaseMock
	{
	}
}
