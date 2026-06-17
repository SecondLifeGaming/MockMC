// Auto-generated mechanical sanity test for DirectionalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Directional;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DirectionalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DirectionalBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getFacing());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getFaces());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DirectionalBaseMock
	{
		@Override
		public Directional clone()
		{
			return null;
		}
	}
}
