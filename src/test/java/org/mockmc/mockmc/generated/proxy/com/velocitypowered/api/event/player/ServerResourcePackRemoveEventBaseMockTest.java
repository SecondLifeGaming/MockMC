// Auto-generated mechanical sanity test for ServerResourcePackRemoveEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerResourcePackRemoveEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerResourcePackRemoveEventBaseMock mock = new ServerResourcePackRemoveEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPackId());
		assertSafeDefault(mock.getServerConnection());
	}
}
