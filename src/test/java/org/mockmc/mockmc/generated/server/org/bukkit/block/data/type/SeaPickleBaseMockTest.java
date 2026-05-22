// Auto-generated mechanical sanity test for SeaPickleBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.SeaPickle;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SeaPickleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SeaPickleBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements SeaPickleBaseMock
	{
		@Override
		public SeaPickle clone()
		{
			return null;
		}
	}
}
