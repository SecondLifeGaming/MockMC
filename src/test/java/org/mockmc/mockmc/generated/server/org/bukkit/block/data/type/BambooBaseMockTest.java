// Auto-generated mechanical sanity test for BambooBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Bamboo;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BambooBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BambooBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getLeaves());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BambooBaseMock
	{
		@Override
		public Bamboo clone()
		{
			return null;
		}
	}
}
