// Auto-generated mechanical sanity test for CommandSourceStackBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class CommandSourceStackBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandSourceStackBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getLocation());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getExecutor());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getSender());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements CommandSourceStackBaseMock
	{
	}
}
