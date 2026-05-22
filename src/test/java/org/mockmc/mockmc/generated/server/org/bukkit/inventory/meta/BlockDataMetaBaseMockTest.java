// Auto-generated mechanical sanity test for BlockDataMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.BlockDataMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockDataMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockDataMetaBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements BlockDataMetaBaseMock
	{
		@Override
		public BlockDataMeta clone()
		{
			return null;
		}
	}
}
