// Auto-generated mechanical sanity test for EnchantingInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EnchantingInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantingInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItem());
		assertSafeDefault(mock.getSecondary());
	}

	private static class Stub implements EnchantingInventoryBaseMock
	{
	}
}
