// Auto-generated mechanical sanity test for ConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ConnectionBaseMock mock = new ConnectionBaseMock()
		{
		};
		assertSafeDefault(mock.unsafe());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getSocketAddress());
	}
}
