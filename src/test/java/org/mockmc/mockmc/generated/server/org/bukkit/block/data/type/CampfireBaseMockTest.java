// Auto-generated mechanical sanity test for CampfireBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Campfire;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class CampfireBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CampfireBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements CampfireBaseMock
	{
		@Override
		public Campfire clone()
		{
			return null;
		}
	}
}
