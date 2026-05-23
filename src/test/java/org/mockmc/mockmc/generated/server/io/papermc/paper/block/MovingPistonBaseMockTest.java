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
		try
		{
			assertSafeDefault(mock.getDirection());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getMovingBlock());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements MovingPistonBaseMock
	{
	}
}
