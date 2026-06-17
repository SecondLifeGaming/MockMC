// Auto-generated mechanical sanity test for FoxBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FoxBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FoxBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getFoxType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getSecondTrustedPlayer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getFirstTrustedPlayer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FoxBaseMock
	{
	}
}
