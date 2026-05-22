// Auto-generated mechanical sanity test for TNTBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.TNT;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TNTBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TNTBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements TNTBaseMock
	{
		@Override
		public TNT clone()
		{
			return null;
		}
	}
}
