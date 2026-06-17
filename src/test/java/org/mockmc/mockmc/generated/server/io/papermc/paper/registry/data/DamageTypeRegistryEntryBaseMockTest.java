// Auto-generated mechanical sanity test for DamageTypeRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DamageTypeRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DamageTypeRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.messageId());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.deathMessageType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.damageScaling());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.damageEffect());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DamageTypeRegistryEntryBaseMock
	{
	}
}
