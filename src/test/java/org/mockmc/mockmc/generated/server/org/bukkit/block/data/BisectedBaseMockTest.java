// Auto-generated mechanical sanity test for BisectedBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Bisected;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BisectedBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BisectedBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHalf());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BisectedBaseMock
	{
		@Override
		public Bisected clone()
		{
			return null;
		}
	}
}
