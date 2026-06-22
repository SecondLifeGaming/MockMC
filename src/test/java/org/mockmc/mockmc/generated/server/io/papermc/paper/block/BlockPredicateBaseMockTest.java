// Auto-generated mechanical sanity test for BlockPredicateBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BlockPredicateBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockPredicateBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.blocks());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BlockPredicateBaseMock
	{
	}
}
