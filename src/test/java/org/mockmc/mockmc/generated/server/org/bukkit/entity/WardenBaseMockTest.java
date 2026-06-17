// Auto-generated mechanical sanity test for WardenBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WardenBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WardenBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAngerLevel());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getEntityAngryAt());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements WardenBaseMock
	{
	}
}
