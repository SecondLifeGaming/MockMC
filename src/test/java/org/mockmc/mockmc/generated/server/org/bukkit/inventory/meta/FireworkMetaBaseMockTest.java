// Auto-generated mechanical sanity test for FireworkMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.FireworkMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FireworkMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FireworkMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getEffects());
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
