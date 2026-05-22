// Auto-generated mechanical sanity test for RegisteredServerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegisteredServerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegisteredServerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.ping());
		assertSafeDefault(mock.getServerInfo());
		assertSafeDefault(mock.getPlayersConnected());
	}

	private static class Stub implements RegisteredServerBaseMock
	{
	}
}
