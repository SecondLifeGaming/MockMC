// Auto-generated mechanical sanity test for EnderCrystalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class EnderCrystalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnderCrystalBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBeamTarget());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EnderCrystalBaseMock
	{
	}
}
