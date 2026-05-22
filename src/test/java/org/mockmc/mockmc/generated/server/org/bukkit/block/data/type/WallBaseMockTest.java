// Auto-generated mechanical sanity test for WallBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Wall;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WallBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WallBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements WallBaseMock
	{
		@Override
		public Wall clone()
		{
			return null;
		}
	}
}
