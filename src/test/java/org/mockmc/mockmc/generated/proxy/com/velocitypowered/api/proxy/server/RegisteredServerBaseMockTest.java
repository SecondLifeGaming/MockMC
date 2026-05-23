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
		try
		{
			assertSafeDefault(mock.ping());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getServerInfo());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getPlayersConnected());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements RegisteredServerBaseMock
	{
	}
}
