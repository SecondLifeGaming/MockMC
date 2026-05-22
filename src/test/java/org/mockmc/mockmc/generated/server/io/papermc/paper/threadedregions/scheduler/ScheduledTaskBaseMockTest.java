// Auto-generated mechanical sanity test for ScheduledTaskBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.threadedregions.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ScheduledTaskBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ScheduledTaskBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.cancel());
		assertSafeDefault(mock.getOwningPlugin());
		assertSafeDefault(mock.getExecutionState());
	}

	private static class Stub implements ScheduledTaskBaseMock
	{
	}
}
