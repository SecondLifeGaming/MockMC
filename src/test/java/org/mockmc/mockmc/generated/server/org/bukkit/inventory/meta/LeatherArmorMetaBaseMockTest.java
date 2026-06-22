// Auto-generated mechanical sanity test for LeatherArmorMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class LeatherArmorMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LeatherArmorMetaBaseMock mock = new Stub();
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
			assertSafeDefault(mock.getColor());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements LeatherArmorMetaBaseMock
	{
		@Override
		public LeatherArmorMeta clone()
		{
			return null;
		}
	}
}
