// Auto-generated mechanical sanity test for AdvancementProgressBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.advancement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class AdvancementProgressBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AdvancementProgressBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getRemainingCriteria());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getAwardedCriteria());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getAdvancement());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements AdvancementProgressBaseMock
	{
	}
}
