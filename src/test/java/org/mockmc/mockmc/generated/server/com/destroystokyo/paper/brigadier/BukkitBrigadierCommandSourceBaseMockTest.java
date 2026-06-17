// Auto-generated mechanical sanity test for BukkitBrigadierCommandSourceBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BukkitBrigadierCommandSourceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitBrigadierCommandSourceBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBukkitLocation());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getBukkitEntity());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getBukkitSender());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getBukkitWorld());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BukkitBrigadierCommandSourceBaseMock
	{
	}
}
