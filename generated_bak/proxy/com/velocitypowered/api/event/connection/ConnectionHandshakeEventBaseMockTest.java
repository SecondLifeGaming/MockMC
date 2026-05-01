// Auto-generated mechanical sanity test for ConnectionHandshakeEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConnectionHandshakeEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ConnectionHandshakeEventBaseMock mock = new ConnectionHandshakeEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getConnection());
		assertSafeDefault(mock.getIntent());
	}
}
