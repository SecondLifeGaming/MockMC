// Auto-generated mechanical sanity test for ShelfBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Shelf;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ShelfBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ShelfBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements ShelfBaseMock
	{
		@Override
		public Shelf clone()
		{
			return null;
		}
	}
}
