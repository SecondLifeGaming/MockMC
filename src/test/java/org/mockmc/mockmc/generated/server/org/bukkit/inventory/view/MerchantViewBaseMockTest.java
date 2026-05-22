// Auto-generated mechanical sanity test for MerchantViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MerchantViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MerchantViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTopInventory());
		assertSafeDefault(mock.getMerchant());
	}

	private static class Stub implements MerchantViewBaseMock
	{
	}
}
