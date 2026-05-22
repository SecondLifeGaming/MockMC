// Auto-generated mechanical sanity test for FarmlandBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Farmland;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FarmlandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FarmlandBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements FarmlandBaseMock
	{
		@Override
		public Farmland clone()
		{
			return null;
		}
	}
}
