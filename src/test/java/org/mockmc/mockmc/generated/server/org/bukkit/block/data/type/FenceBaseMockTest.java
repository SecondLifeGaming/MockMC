// Auto-generated mechanical sanity test for FenceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Fence;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class FenceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FenceBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements FenceBaseMock
	{
		@Override
		public Fence clone()
		{
			return null;
		}
	}
}
