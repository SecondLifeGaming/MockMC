// Auto-generated mechanical sanity test for ServiceEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ServiceEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServiceEventBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getProvider());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ServiceEventBaseMock
	{
	}
}
