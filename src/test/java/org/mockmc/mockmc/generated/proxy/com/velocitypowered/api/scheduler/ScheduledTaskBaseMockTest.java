// Auto-generated mechanical sanity test for ScheduledTaskBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.scheduler;

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
		assertSafeDefault(mock.status());
		assertSafeDefault(mock.plugin());
	}
}
