// Auto-generated mechanical sanity test for ConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		ConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.unsafe());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getSocketAddress());
	}

	private static class Stub implements ConnectionBaseMock
	{
	}
}
