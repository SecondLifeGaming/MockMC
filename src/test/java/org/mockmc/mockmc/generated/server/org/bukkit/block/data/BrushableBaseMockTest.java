// Auto-generated mechanical sanity test for BrushableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Brushable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BrushableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrushableBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements BrushableBaseMock
	{
		@Override
		public Brushable clone()
		{
			return null;
		}
	}
}
