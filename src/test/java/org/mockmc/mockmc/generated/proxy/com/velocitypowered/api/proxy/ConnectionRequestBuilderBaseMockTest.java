// Auto-generated mechanical sanity test for ConnectionRequestBuilderBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ConnectionRequestBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConnectionRequestBuilderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.connect());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getServer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.connectWithIndication());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ConnectionRequestBuilderBaseMock
	{
	}
}
