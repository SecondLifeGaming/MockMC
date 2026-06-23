// Auto-generated mechanical sanity test for SlabBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Slab;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class SlabBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SlabBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SlabBaseMock
	{
		@Override
		public Slab clone()
		{
			return null;
		}
	}
}
