// Auto-generated mechanical sanity test for WritableBookMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.WritableBookMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WritableBookMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WritableBookMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getPages());
	}

	private static class Stub implements WritableBookMetaBaseMock
	{
		@Override
		public WritableBookMeta clone()
		{
			return null;
		}
	}
}
