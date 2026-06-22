// Auto-generated mechanical sanity test for LeavesBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Leaves;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class LeavesBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LeavesBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements LeavesBaseMock
	{
		@Override
		public Leaves clone()
		{
			return null;
		}
	}
}
