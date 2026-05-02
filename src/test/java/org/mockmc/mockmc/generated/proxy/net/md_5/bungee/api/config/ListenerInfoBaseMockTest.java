// Auto-generated mechanical sanity test for ListenerInfoBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.config;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ListenerInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ListenerInfoBaseMock mock = new ListenerInfoBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getHost());
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getForcedHosts());
		assertSafeDefault(mock.getSocketAddress());
		assertSafeDefault(mock.getDefaultServer());
		assertSafeDefault(mock.getFallbackServer());
		assertSafeDefault(mock.getServerPriority());
		assertSafeDefault(mock.getTabListType());
	}
}
