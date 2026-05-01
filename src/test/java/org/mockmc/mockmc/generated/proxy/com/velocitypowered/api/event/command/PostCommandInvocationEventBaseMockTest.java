// Auto-generated mechanical sanity test for PostCommandInvocationEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PostCommandInvocationEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PostCommandInvocationEventBaseMock mock = new PostCommandInvocationEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getCommand());
		assertSafeDefault(mock.getCommandSource());
	}
}
