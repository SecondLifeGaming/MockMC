// Auto-generated mechanical sanity test for CrossbowMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.CrossbowMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CrossbowMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CrossbowMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getChargedProjectiles());
	}

	private static class Stub implements CrossbowMetaBaseMock
	{
		@Override
		public CrossbowMeta clone()
		{
			return null;
		}
	}
}
