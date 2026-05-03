// Auto-generated mechanical sanity test for ScheduledTaskBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ScheduledTaskBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		ScheduledTaskBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.status());
		assertSafeDefault(mock.plugin());
	}

	private static class Stub implements ScheduledTaskBaseMock
	{
	}
}
