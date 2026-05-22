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
		assertSafeDefault(mock.getActiveWorkers());
		assertSafeDefault(mock.getPendingTasks());
	}

	private static class Stub implements BukkitSchedulerBaseMock
	{
	}
}
