// Auto-generated mechanical sanity test for DecoratedPotInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DecoratedPotInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DecoratedPotInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItem());
		assertSafeDefault(mock.getHolder());
	}

	private static class Stub implements DecoratedPotInventoryBaseMock
	{
	}
}
