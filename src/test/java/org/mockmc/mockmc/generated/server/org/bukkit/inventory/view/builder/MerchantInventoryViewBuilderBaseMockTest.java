// Auto-generated mechanical sanity test for MerchantInventoryViewBuilderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.InventoryView;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MerchantInventoryViewBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MerchantInventoryViewBuilderBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.copy());
	}

	private static class Stub<V extends InventoryView> implements MerchantInventoryViewBuilderBaseMock<V>
	{
	}
}
