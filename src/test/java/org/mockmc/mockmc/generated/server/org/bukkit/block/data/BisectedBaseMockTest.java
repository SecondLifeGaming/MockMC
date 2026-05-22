// Auto-generated mechanical sanity test for BisectedBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Bisected;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BisectedBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BisectedBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHalf());
	}

	private static class Stub implements BisectedBaseMock
	{
		@Override
		public Bisected clone()
		{
			return null;
		}
	}
}
