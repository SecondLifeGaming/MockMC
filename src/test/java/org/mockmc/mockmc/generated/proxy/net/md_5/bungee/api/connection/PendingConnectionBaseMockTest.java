// Auto-generated mechanical sanity test for PendingConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PendingConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PendingConnectionBaseMock mock = new PendingConnectionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getVirtualHost());
		assertSafeDefault(mock.getListener());
		assertSafeDefault(mock.getUUID());
	}
}
