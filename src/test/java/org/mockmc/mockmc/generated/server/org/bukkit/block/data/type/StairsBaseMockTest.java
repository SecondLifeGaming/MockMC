// Auto-generated mechanical sanity test for StairsBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Stairs;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StairsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StairsBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getShape());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements StairsBaseMock
	{
		@Override
		public Stairs clone()
		{
			return null;
		}
	}
}
