// Auto-generated mechanical sanity test for StairsBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Stairs;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StairsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StairsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getShape());
	}

	private static class Stub implements StairsBaseMock
	{
		@Override
		public Stairs clone()
		{
			return null;
		}
	}
}
