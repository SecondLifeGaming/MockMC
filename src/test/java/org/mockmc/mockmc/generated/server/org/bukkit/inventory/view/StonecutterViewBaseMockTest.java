// Auto-generated mechanical sanity test for StonecutterViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StonecutterViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StonecutterViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRecipes());
		assertSafeDefault(mock.getTopInventory());
	}

	private static class Stub implements StonecutterViewBaseMock
	{
	}
}
