// Auto-generated mechanical sanity test for LeatherArmorMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LeatherArmorMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LeatherArmorMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getColor());
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
