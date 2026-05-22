// Auto-generated mechanical sanity test for BrewingStandBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.BrewingStand;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BrewingStandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrewingStandBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBottles());
	}

	private static class Stub implements BrewingStandBaseMock
	{
		@Override
		public BrewingStand clone()
		{
			return null;
		}
	}
}
