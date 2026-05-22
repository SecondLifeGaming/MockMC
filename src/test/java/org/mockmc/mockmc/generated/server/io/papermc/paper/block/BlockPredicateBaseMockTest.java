// Auto-generated mechanical sanity test for BlockPredicateBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BlockPredicateBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockPredicateBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.blocks());
	}

	private static class Stub implements BlockPredicateBaseMock
	{
	}
}
