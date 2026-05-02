// Auto-generated mechanical sanity test for ConsoleCommandSenderBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConsoleCommandSenderBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ConsoleCommandSenderBaseMock mock = new ConsoleCommandSenderBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getPermissions());
		assertSafeDefault(mock.getGroups());
	}
}
