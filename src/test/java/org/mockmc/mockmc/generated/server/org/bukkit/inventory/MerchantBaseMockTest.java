// Auto-generated mechanical sanity test for MerchantBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MerchantBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MerchantBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRecipes());
		assertSafeDefault(mock.getTrader());
	}

	private static class Stub implements MerchantBaseMock
	{
	}
}
