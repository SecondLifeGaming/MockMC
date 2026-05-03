// Auto-generated mechanical sanity test for ScheduledTaskBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ScheduledTaskBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ScheduledTaskBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOwner());
		assertSafeDefault(mock.getTask());
	}

	private static class Stub implements ScheduledTaskBaseMock
	{
	}
}
