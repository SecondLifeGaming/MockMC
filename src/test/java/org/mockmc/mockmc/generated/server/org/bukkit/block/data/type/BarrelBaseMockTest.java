// Auto-generated mechanical sanity test for BarrelBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Barrel;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BarrelBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BarrelBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements BarrelBaseMock
	{
		@Override
		public Barrel clone()
		{
			return null;
		}
	}
}
