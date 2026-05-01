// Auto-generated mechanical sanity test for TabCompleteResponseBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TabCompleteResponseBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		TabCompleteResponseBaseMock mock = new TabCompleteResponseBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getCommands());
		assertSafeDefault(mock.getSuggestions());
	}
}
