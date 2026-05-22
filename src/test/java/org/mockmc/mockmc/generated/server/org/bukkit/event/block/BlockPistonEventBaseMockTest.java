// Auto-generated mechanical sanity test for BlockPistonEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockPistonEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockPistonEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDirection());
	}

	private static class Stub implements BlockPistonEventBaseMock
	{
	}
}
