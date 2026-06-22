// Auto-generated mechanical sanity test for BlockStateMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.BlockStateMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BlockStateMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockStateMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBlockState());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BlockStateMetaBaseMock
	{
		@Override
		public BlockStateMeta clone()
		{
			return null;
		}
	}
}
