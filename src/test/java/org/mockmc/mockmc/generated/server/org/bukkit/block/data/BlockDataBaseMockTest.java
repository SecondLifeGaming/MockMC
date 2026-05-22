// Auto-generated mechanical sanity test for BlockDataBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.BlockData;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockDataBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockDataBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getAsString());
		assertSafeDefault(mock.getMaterial());
		assertSafeDefault(mock.createBlockState());
		assertSafeDefault(mock.getPistonMoveReaction());
		assertSafeDefault(mock.getSoundGroup());
		assertSafeDefault(mock.getMapColor());
		assertSafeDefault(mock.getPlacementMaterial());
	}

	private static class Stub implements BlockDataBaseMock
	{
		@Override
		public BlockData clone()
		{
			return null;
		}
	}
}
