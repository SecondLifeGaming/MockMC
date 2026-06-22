// Auto-generated mechanical sanity test for CombatEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.world.damagesource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class CombatEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CombatEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getDamageSource());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getFallLocationType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements CombatEntryBaseMock
	{
	}
}
