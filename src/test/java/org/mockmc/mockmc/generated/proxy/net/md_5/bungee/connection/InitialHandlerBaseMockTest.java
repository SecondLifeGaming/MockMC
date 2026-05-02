// Auto-generated mechanical sanity test for InitialHandlerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class InitialHandlerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		InitialHandlerBaseMock mock = new InitialHandlerBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.unsafe());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getVirtualHost());
		assertSafeDefault(mock.getListener());
		assertSafeDefault(mock.getUUID());
		assertSafeDefault(mock.getClientBrand());
		assertSafeDefault(mock.getHandshake());
		assertSafeDefault(mock.getLoginProfile());
		assertSafeDefault(mock.getExtraDataInHandshake());
		assertSafeDefault(mock.getRewriteId());
		assertSafeDefault(mock.getBrandMessage());
		assertSafeDefault(mock.getRegisteredChannels());
		assertSafeDefault(mock.getSocketAddress());
		assertSafeDefault(mock.getOfflineId());
		assertSafeDefault(mock.getLoginRequest());
	}
}
