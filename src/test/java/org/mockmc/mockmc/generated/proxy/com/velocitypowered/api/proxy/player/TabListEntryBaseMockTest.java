// Auto-generated mechanical sanity test for TabListEntryBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TabListEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		TabListEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getProfile());
		assertSafeDefault(mock.getDisplayNameComponent());
		assertSafeDefault(mock.getIdentifiedKey());
		assertSafeDefault(mock.getTabList());
		assertSafeDefault(mock.getChatSession());
	}

	private static class Stub implements TabListEntryBaseMock
	{
	}
}
