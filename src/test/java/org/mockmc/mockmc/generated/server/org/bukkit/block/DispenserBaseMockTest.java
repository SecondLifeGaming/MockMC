// Auto-generated mechanical sanity test for DispenserBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class DispenserBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DispenserBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBlockProjectileSource());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DispenserBaseMock
	{
	}
}
