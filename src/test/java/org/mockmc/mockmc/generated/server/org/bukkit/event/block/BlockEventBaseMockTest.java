// Auto-generated mechanical sanity test for BlockEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBlock());
	}

	private static class Stub implements BlockEventBaseMock
	{
	}
}
