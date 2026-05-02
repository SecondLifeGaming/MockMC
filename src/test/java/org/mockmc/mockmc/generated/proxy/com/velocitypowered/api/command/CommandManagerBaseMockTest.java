// Auto-generated mechanical sanity test for CommandManagerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CommandManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CommandManagerBaseMock mock = new CommandManagerBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getAliases());
	}
}
