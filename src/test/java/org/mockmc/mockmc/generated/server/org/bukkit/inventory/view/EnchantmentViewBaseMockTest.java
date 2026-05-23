// Auto-generated mechanical sanity test for EnchantmentViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EnchantmentViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantmentViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOffers());
		assertSafeDefault(mock.getTopInventory());
	}

	private static class Stub implements EnchantmentViewBaseMock
	{
	}
}
