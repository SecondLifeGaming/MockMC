// Auto-generated mechanical sanity test for EntityEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EntityEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EntityEventBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getEntity());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getEntityType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EntityEventBaseMock
	{
	}
}
