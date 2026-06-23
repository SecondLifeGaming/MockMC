// Auto-generated mechanical sanity test for ThrowableProjectileBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ThrowableProjectileBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ThrowableProjectileBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getItem());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ThrowableProjectileBaseMock
	{
	}
}
