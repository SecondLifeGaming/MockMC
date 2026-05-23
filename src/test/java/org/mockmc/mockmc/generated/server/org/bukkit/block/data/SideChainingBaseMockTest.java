// Auto-generated mechanical sanity test for SideChainingBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.SideChaining;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SideChainingBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SideChainingBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSideChain());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SideChainingBaseMock
	{
		@Override
		public SideChaining clone()
		{
			return null;
		}
	}
}
