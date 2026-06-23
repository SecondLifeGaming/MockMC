// Auto-generated mechanical sanity test for MinecraftExperimentalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class MinecraftExperimentalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MinecraftExperimentalBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.value());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements MinecraftExperimentalBaseMock
	{
	}
}
