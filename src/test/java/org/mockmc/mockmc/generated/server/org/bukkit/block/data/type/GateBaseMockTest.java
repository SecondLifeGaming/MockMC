// Auto-generated mechanical sanity test for GateBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Gate;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class GateBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		GateBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements GateBaseMock
	{
		@Override
		public Gate clone()
		{
			return null;
		}
	}
}
