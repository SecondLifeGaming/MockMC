// Auto-generated mechanical sanity test for ChiseledBookshelfBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.ChiseledBookshelf;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ChiseledBookshelfBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChiseledBookshelfBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getOccupiedSlots());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ChiseledBookshelfBaseMock
	{
		@Override
		public ChiseledBookshelf clone()
		{
			return null;
		}
	}
}
