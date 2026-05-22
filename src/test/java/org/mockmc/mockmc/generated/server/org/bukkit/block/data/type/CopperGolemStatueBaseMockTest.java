// Auto-generated mechanical sanity test for CopperGolemStatueBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.CopperGolemStatue;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CopperGolemStatueBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CopperGolemStatueBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCopperGolemPose());
	}

	private static class Stub implements CopperGolemStatueBaseMock
	{
		@Override
		public CopperGolemStatue clone()
		{
			return null;
		}
	}
}
