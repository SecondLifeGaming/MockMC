// Auto-generated mechanical sanity test for FireworkMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.FireworkMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class FireworkMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FireworkMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.clone());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getEffects());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FireworkMetaBaseMock
	{
		@Override
		public FireworkMeta clone()
		{
			return null;
		}
	}
}
