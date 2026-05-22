// Auto-generated mechanical sanity test for OminousBottleMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.OminousBottleMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class OminousBottleMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OminousBottleMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
	}

	private static class Stub implements OminousBottleMetaBaseMock
	{
		@Override
		public OminousBottleMeta clone()
		{
			return null;
		}
	}
}
