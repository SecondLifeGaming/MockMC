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
		try
		{
			assertSafeDefault(mock.clone());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
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
