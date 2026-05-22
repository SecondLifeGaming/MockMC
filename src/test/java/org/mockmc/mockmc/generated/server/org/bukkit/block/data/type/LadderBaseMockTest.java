// Auto-generated mechanical sanity test for LadderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Ladder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LadderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LadderBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements LadderBaseMock
	{
		@Override
		public Ladder clone()
		{
			return null;
		}
	}
}
