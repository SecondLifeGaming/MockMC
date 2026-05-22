// Auto-generated mechanical sanity test for MovingPistonBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MovingPistonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MovingPistonBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDirection());
		assertSafeDefault(mock.getMovingBlock());
	}

	private static class Stub implements MovingPistonBaseMock
	{
	}
}
