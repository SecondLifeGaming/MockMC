// Auto-generated mechanical sanity test for BannerMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.BannerMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BannerMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BannerMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPatterns());
	}

	private static class Stub implements BannerMetaBaseMock
	{
		@Override
		public BannerMeta clone()
		{
			return null;
		}
	}
}
