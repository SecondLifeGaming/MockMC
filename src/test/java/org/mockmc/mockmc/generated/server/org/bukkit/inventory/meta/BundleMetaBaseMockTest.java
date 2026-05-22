// Auto-generated mechanical sanity test for BundleMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.BundleMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BundleMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BundleMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItems());
	}

	private static class Stub implements BundleMetaBaseMock
	{
		@Override
		public BundleMeta clone()
		{
			return null;
		}
	}
}
