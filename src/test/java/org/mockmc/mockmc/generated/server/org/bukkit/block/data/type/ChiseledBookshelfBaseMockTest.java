// Auto-generated mechanical sanity test for ChiseledBookshelfBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.ChiseledBookshelf;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChiseledBookshelfBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChiseledBookshelfBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOccupiedSlots());
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
