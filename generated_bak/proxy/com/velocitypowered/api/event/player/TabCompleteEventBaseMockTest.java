// Auto-generated mechanical sanity test for TabCompleteEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TabCompleteEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		TabCompleteEventBaseMock mock = new TabCompleteEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getSuggestions());
		assertSafeDefault(mock.getPartialMessage());
	}
}
