// Auto-generated mechanical sanity test for ScheduledTaskBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.scheduler;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ScheduledTaskBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ScheduledTaskBaseMock mock = new ScheduledTaskBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getOwner());
		assertSafeDefault(mock.getTask());
	}
}
