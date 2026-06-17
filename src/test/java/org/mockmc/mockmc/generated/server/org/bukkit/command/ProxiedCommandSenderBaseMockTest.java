// Auto-generated mechanical sanity test for ProxiedCommandSenderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ProxiedCommandSenderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ProxiedCommandSenderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getCaller());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getCallee());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.audience());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ProxiedCommandSenderBaseMock
	{
	}
}
