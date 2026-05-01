// Auto-generated mechanical sanity test for ProxiedPlayerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxiedPlayerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxiedPlayerBaseMock mock = new ProxiedPlayerBaseMock()
		{
		};
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.getLocale());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getMainHand());
		assertSafeDefault(mock.getSkinParts());
		assertSafeDefault(mock.getScoreboard());
		assertSafeDefault(mock.getUUID());
		assertSafeDefault(mock.getClientBrand());
		assertSafeDefault(mock.getChatMode());
		assertSafeDefault(mock.getPendingConnection());
		assertSafeDefault(mock.getModList());
		assertSafeDefault(mock.getReconnectServer());
	}
}
