// Auto-generated mechanical sanity test for BlockDisplayBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockDisplayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockDisplayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBlock());
	}

	private static class Stub implements BlockDisplayBaseMock
	{
	}
}
