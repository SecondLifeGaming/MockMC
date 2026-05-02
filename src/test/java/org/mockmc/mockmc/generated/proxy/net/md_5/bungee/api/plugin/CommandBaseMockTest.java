// Auto-generated mechanical sanity test for CommandBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.plugin;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CommandBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CommandBaseMock mock = new CommandBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPermissionMessage());
		assertSafeDefault(mock.getAliases());
		assertSafeDefault(mock.getPermission());
	}
}
