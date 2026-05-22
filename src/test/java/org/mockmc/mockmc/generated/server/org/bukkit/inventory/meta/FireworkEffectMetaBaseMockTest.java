// Auto-generated mechanical sanity test for FireworkEffectMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FireworkEffectMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FireworkEffectMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getEffect());
	}

	private static class Stub implements FireworkEffectMetaBaseMock
	{
		@Override
		public FireworkEffectMeta clone()
		{
			return null;
		}
	}
}
