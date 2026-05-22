// Auto-generated mechanical sanity test for FinePositionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FinePositionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FinePositionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.toBlock());
	}

	private static class Stub implements FinePositionBaseMock
	{
	}
}
