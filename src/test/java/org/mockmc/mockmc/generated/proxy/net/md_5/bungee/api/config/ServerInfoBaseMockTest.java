// Auto-generated mechanical sanity test for ServerInfoBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class ServerInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServerInfoBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getPermission());
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getSocketAddress());
	}

	private static class Stub implements ServerInfoBaseMock
	{
	}
}
