// Auto-generated mechanical sanity test for FurnaceInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FurnaceInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FurnaceInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getHolder());
		assertSafeDefault(mock.getFuel());
		assertSafeDefault(mock.getSmelting());
	}

	private static class Stub implements FurnaceInventoryBaseMock
	{
	}
}
