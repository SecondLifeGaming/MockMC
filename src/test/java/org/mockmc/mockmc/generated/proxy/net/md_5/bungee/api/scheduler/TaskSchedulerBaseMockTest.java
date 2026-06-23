// Auto-generated mechanical sanity test for TaskSchedulerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class TaskSchedulerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TaskSchedulerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.unsafe());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TaskSchedulerBaseMock
	{
	}
}
