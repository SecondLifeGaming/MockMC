// Auto-generated mechanical sanity test for RailBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Rail;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RailBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RailBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getShape());
		assertSafeDefault(mock.getShapes());
	}

	private static class Stub implements RailBaseMock
	{
		@Override
		public Rail clone()
		{
			return null;
		}
	}
}
