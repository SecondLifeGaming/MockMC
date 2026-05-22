// Auto-generated mechanical sanity test for LootableBlockInventoryBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.loottable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LootableBlockInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LootableBlockInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBlock());
	}

	private static class Stub implements LootableBlockInventoryBaseMock
	{
	}
}
