// Auto-generated mechanical sanity test for EnderChestBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.EnderChest;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EnderChestBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnderChestBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements EnderChestBaseMock
	{
		@Override
		public EnderChest clone()
		{
			return null;
		}
	}
}
