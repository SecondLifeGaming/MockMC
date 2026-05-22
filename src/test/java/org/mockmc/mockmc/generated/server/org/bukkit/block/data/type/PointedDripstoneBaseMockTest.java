// Auto-generated mechanical sanity test for PointedDripstoneBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.PointedDripstone;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PointedDripstoneBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PointedDripstoneBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVerticalDirection());
		assertSafeDefault(mock.getVerticalDirections());
		assertSafeDefault(mock.getThickness());
	}

	private static class Stub implements PointedDripstoneBaseMock
	{
		@Override
		public PointedDripstone clone()
		{
			return null;
		}
	}
}
