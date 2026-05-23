// Auto-generated mechanical sanity test for BukkitWorkerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BukkitWorkerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitWorkerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getOwner());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getThread());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BukkitWorkerBaseMock
	{
	}
}
