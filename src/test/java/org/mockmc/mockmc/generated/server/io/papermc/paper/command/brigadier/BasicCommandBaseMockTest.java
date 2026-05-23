// Auto-generated mechanical sanity test for BasicCommandBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BasicCommandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BasicCommandBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.permission());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BasicCommandBaseMock
	{
	}
}
