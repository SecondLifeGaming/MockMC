// Auto-generated mechanical sanity test for SideChainingBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.SideChaining;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SideChainingBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SideChainingBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSideChain());
	}

	private static class Stub implements SideChainingBaseMock
	{
		@Override
		public SideChaining clone()
		{
			return null;
		}
	}
}
