// Auto-generated mechanical sanity test for RepairableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.Repairable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RepairableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RepairableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
	}

	private static class Stub implements RepairableBaseMock
	{
		@Override
		public Repairable clone()
		{
			return null;
		}
	}
}
