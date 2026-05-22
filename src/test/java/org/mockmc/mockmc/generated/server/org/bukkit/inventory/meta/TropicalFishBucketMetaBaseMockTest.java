// Auto-generated mechanical sanity test for TropicalFishBucketMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TropicalFishBucketMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TropicalFishBucketMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getPattern());
		assertSafeDefault(mock.getPatternColor());
		assertSafeDefault(mock.getBodyColor());
	}

	private static class Stub implements TropicalFishBucketMetaBaseMock
	{
		@Override
		public TropicalFishBucketMeta clone()
		{
			return null;
		}
	}
}
