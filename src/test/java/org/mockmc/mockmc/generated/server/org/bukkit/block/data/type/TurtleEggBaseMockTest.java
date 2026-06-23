// Auto-generated mechanical sanity test for TurtleEggBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.TurtleEgg;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class TurtleEggBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TurtleEggBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements TurtleEggBaseMock
	{
		@Override
		public TurtleEgg clone()
		{
			return null;
		}
	}
}
