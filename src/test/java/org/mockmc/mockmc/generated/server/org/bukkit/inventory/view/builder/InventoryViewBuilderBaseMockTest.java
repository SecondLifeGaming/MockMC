// Auto-generated mechanical sanity test for InventoryViewBuilderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.InventoryView;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class InventoryViewBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		InventoryViewBuilderBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.copy());
	}

	private static class Stub<V extends InventoryView> implements InventoryViewBuilderBaseMock<V>
	{
	}
}
