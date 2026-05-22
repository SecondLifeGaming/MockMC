// Auto-generated mechanical sanity test for PositionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PositionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PositionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.toVector());
		assertSafeDefault(mock.toBlock());
		assertSafeDefault(mock.toCenter());
	}

	private static class Stub implements PositionBaseMock
	{
	}
}
