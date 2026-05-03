// Auto-generated mechanical sanity test for CommandBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		CommandBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPermission());
		assertSafeDefault(mock.getPermissionMessage());
		assertSafeDefault(mock.getAliases());
	}

	private static class Stub implements CommandBaseMock
	{
	}
}
