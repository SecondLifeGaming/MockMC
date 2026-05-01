// Auto-generated mechanical sanity test for CommandMetaBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CommandMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CommandMetaBaseMock mock = new CommandMetaBaseMock()
		{
		};
		assertSafeDefault(mock.getPlugin());
		assertSafeDefault(mock.getAliases());
		assertSafeDefault(mock.getHints());
	}
}
