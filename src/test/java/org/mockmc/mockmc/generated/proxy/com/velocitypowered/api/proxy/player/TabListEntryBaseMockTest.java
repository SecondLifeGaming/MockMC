// Auto-generated mechanical sanity test for TabListEntryBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TabListEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		TabListEntryBaseMock mock = new TabListEntryBaseMock()
		{
		};
		assertSafeDefault(mock.getProfile());
		assertSafeDefault(mock.getDisplayNameComponent());
		assertSafeDefault(mock.getIdentifiedKey());
		assertSafeDefault(mock.getTabList());
		assertSafeDefault(mock.getChatSession());
	}
}
