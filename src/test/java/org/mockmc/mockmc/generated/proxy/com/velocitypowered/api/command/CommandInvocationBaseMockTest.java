// Auto-generated mechanical sanity test for CommandInvocationBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CommandInvocationBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CommandInvocationBaseMock mock = new CommandInvocationBaseMock()
		{
		};
		assertSafeDefault(mock.source());
		assertSafeDefault(mock.arguments());
	}
}
