// Auto-generated mechanical sanity test for DisconnectEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DisconnectEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		DisconnectEventBaseMock mock = new DisconnectEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getLoginStatus());
	}
}
