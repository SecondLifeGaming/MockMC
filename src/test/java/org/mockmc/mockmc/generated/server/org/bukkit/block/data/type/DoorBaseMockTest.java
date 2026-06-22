// Auto-generated mechanical sanity test for DoorBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Door;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class DoorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DoorBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHinge());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DoorBaseMock
	{
		@Override
		public Door clone()
		{
			return null;
		}
	}
}
