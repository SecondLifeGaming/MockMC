// Auto-generated mechanical sanity test for FurnaceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Furnace;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class FurnaceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FurnaceBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements FurnaceBaseMock
	{
		@Override
		public Furnace clone()
		{
			return null;
		}
	}
}
