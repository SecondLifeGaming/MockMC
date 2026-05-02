// Auto-generated mechanical sanity test for ServerPreConnectEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerPreConnectEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerPreConnectEventBaseMock mock = new ServerPreConnectEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getPreviousServer());
		assertSafeDefault(mock.getOriginalServer());
	}
}
