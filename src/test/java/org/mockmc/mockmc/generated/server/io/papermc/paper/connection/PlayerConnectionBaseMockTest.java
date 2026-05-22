// Auto-generated mechanical sanity test for PlayerConnectionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getHAProxyAddress());
		assertSafeDefault(mock.getVirtualHost());
		assertSafeDefault(mock.getClientAddress());
	}

	private static class Stub implements PlayerConnectionBaseMock
	{
	}
}
