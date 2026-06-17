// Auto-generated mechanical sanity test for BaseSpawnerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.spawner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BaseSpawnerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BaseSpawnerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSpawnedEntity());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getSpawnedType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getPotentialSpawns());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BaseSpawnerBaseMock
	{
	}
}
