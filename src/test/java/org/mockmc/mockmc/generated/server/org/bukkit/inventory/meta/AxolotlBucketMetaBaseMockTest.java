// Auto-generated mechanical sanity test for AxolotlBucketMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AxolotlBucketMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AxolotlBucketMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getVariant());
	}

	private static class Stub implements AxolotlBucketMetaBaseMock
	{
		@Override
		public AxolotlBucketMeta clone()
		{
			return null;
		}
	}
}
