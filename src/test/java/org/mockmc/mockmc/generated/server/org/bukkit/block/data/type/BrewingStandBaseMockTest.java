// Auto-generated mechanical sanity test for BrewingStandBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.BrewingStand;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BrewingStandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrewingStandBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBottles());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BrewingStandBaseMock
	{
		@Override
		public BrewingStand clone()
		{
			return null;
		}
	}
}
