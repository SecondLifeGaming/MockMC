// Auto-generated mechanical sanity test for ScheduledTaskBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ScheduledTaskBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ScheduledTaskBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getOwner());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getTask());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ScheduledTaskBaseMock
	{
	}
}
