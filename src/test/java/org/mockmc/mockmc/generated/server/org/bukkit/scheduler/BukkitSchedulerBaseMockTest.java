// Auto-generated mechanical sanity test for BukkitSchedulerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BukkitSchedulerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitSchedulerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPendingTasks());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getActiveWorkers());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BukkitSchedulerBaseMock
	{
	}
}
