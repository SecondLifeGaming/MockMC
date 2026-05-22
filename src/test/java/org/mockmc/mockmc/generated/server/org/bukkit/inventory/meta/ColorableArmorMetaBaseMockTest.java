// Auto-generated mechanical sanity test for ColorableArmorMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ColorableArmorMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ColorableArmorMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
	}

	private static class Stub implements ColorableArmorMetaBaseMock
	{
		@Override
		public ColorableArmorMeta clone()
		{
			return null;
		}
	}
}
