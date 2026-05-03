// Auto-generated mechanical sanity test for TaskSchedulerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TaskSchedulerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		TaskSchedulerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.unsafe());
	}

	private static class Stub implements TaskSchedulerBaseMock
	{
	}
}
