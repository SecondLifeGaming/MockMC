// Auto-generated mechanical sanity test for ShieldMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.ShieldMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ShieldMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ShieldMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBaseColor());
	}

	private static class Stub implements ShieldMetaBaseMock
	{
		@Override
		public ShieldMeta clone()
		{
			return null;
		}
	}
}
