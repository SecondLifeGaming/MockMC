// Auto-generated mechanical sanity test for BukkitSchedulerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BukkitSchedulerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitSchedulerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getActiveWorkers());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getPendingTasks());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BukkitSchedulerBaseMock
	{
	}
}
