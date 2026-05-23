// Auto-generated mechanical sanity test for NetworkClientBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class NetworkClientBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		NetworkClientBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAddress());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getVirtualHost());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements NetworkClientBaseMock
	{
	}
}
