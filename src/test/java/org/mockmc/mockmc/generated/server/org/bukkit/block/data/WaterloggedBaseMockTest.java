// Auto-generated mechanical sanity test for WaterloggedBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Waterlogged;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WaterloggedBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WaterloggedBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements WaterloggedBaseMock
	{
		@Override
		public Waterlogged clone()
		{
			return null;
		}
	}
}
