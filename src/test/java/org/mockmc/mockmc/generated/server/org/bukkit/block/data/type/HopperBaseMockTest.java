// Auto-generated mechanical sanity test for HopperBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Hopper;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HopperBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HopperBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements HopperBaseMock
	{
		@Override
		public Hopper clone()
		{
			return null;
		}
	}
}
