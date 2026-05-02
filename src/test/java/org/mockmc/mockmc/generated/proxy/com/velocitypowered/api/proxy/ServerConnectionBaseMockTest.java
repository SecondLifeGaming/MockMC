// Auto-generated mechanical sanity test for ServerConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerConnectionBaseMock mock = new ServerConnectionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getPreviousServer());
		assertSafeDefault(mock.getServerInfo());
	}
}
