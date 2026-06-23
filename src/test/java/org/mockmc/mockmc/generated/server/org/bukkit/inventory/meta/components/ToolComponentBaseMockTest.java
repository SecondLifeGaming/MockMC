// Auto-generated mechanical sanity test for ToolComponentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ToolComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ToolComponentBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getRules());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ToolComponentBaseMock
	{
	}
}
