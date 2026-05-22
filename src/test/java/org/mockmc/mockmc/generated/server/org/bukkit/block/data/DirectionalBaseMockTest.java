// Auto-generated mechanical sanity test for DirectionalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Directional;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DirectionalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DirectionalBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFaces());
		assertSafeDefault(mock.getFacing());
	}

	private static class Stub implements DirectionalBaseMock
	{
		@Override
		public Directional clone()
		{
			return null;
		}
	}
}
