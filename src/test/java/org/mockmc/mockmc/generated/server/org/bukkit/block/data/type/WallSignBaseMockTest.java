// Auto-generated mechanical sanity test for WallSignBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.WallSign;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WallSignBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WallSignBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements WallSignBaseMock
	{
		@Override
		public WallSign clone()
		{
			return null;
		}
	}
}
