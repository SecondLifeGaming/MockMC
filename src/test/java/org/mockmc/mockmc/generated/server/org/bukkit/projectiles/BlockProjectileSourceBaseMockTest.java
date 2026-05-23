// Auto-generated mechanical sanity test for BlockProjectileSourceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.projectiles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockProjectileSourceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockProjectileSourceBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBlock());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BlockProjectileSourceBaseMock
	{
	}
}
