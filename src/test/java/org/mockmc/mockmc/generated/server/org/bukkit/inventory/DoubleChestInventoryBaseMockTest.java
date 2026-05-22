// Auto-generated mechanical sanity test for DoubleChestInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DoubleChestInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DoubleChestInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLeftSide());
		assertSafeDefault(mock.getHolder());
		assertSafeDefault(mock.getRightSide());
	}

	private static class Stub implements DoubleChestInventoryBaseMock
	{
	}
}
