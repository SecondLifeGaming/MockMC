// Auto-generated mechanical sanity test for CakeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Cake;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class CakeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CakeBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements CakeBaseMock
	{
		@Override
		public Cake clone()
		{
			return null;
		}
	}
}
