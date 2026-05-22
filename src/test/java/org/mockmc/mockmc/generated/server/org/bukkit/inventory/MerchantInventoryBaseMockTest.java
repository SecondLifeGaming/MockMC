// Auto-generated mechanical sanity test for MerchantInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MerchantInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MerchantInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMerchant());
		assertSafeDefault(mock.getSelectedRecipe());
	}

	private static class Stub implements MerchantInventoryBaseMock
	{
	}
}
