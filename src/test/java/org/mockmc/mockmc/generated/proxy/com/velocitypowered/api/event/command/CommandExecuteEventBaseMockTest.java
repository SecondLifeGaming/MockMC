// Auto-generated mechanical sanity test for CommandExecuteEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CommandExecuteEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CommandExecuteEventBaseMock mock = new CommandExecuteEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getCommand());
		assertSafeDefault(mock.getCommandSource());
		assertSafeDefault(mock.getInvocationInfo());
	}
}
