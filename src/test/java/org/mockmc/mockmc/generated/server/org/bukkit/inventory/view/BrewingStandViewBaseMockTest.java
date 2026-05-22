// Auto-generated mechanical sanity test for BrewingStandViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BrewingStandViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrewingStandViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTopInventory());
	}

	private static class Stub implements BrewingStandViewBaseMock
	{
	}
}
