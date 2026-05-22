// Auto-generated mechanical sanity test for LanternBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Lantern;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LanternBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LanternBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements LanternBaseMock
	{
		@Override
		public Lantern clone()
		{
			return null;
		}
	}
}
