// Auto-generated mechanical sanity test for ZombieNautilusBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ZombieNautilusBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ZombieNautilusBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getVariant());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ZombieNautilusBaseMock
	{
	}
}
