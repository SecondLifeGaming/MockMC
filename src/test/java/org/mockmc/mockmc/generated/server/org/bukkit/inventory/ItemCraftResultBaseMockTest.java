// Auto-generated mechanical sanity test for ItemCraftResultBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemCraftResultBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemCraftResultBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getResultingMatrix());
		assertSafeDefault(mock.getOverflowItems());
	}

	private static class Stub implements ItemCraftResultBaseMock
	{
	}
}
