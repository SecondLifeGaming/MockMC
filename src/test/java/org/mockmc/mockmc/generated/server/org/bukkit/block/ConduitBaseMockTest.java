// Auto-generated mechanical sanity test for ConduitBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConduitBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConduitBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getFrameBlocks());
		assertSafeDefault(mock.getHuntingArea());
	}

	private static class Stub implements ConduitBaseMock
	{
	}
}
