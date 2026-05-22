// Auto-generated mechanical sanity test for SaplingBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Sapling;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SaplingBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SaplingBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements SaplingBaseMock
	{
		@Override
		public Sapling clone()
		{
			return null;
		}
	}
}
