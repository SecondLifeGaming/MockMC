// Auto-generated mechanical sanity test for PistonHeadBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.PistonHead;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PistonHeadBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PistonHeadBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements PistonHeadBaseMock
	{
		@Override
		public PistonHead clone()
		{
			return null;
		}
	}
}
