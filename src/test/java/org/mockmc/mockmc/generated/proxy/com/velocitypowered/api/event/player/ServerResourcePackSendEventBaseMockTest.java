// Auto-generated mechanical sanity test for ServerResourcePackSendEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerResourcePackSendEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerResourcePackSendEventBaseMock mock = new ServerResourcePackSendEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getServerConnection());
		assertSafeDefault(mock.getReceivedResourcePack());
		assertSafeDefault(mock.getProvidedResourcePack());
	}
}
