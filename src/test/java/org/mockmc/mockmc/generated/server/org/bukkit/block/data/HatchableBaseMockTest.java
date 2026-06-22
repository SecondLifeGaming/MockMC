// Auto-generated mechanical sanity test for HatchableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Hatchable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class HatchableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HatchableBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements HatchableBaseMock
	{
		@Override
		public Hatchable clone()
		{
			return null;
		}
	}
}
