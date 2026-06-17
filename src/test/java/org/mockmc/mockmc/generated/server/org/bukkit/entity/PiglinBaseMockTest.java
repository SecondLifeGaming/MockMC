// Auto-generated mechanical sanity test for PiglinBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PiglinBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PiglinBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBarterList());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getInterestList());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PiglinBaseMock
	{
	}
}
