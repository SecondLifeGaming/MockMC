// Auto-generated mechanical sanity test for MerchantInventoryViewBuilderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.InventoryView;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class MerchantInventoryViewBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MerchantInventoryViewBuilderBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.copy());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<V extends InventoryView> implements MerchantInventoryViewBuilderBaseMock<V>
	{
	}
}
