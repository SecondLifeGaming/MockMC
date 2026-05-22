// Auto-generated mechanical sanity test for BrewerInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BrewerInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrewerInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHolder());
		assertSafeDefault(mock.getFuel());
		assertSafeDefault(mock.getIngredient());
	}

	private static class Stub implements BrewerInventoryBaseMock
	{
	}
}
