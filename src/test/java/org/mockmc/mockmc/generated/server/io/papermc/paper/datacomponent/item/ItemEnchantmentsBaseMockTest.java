// Auto-generated mechanical sanity test for ItemEnchantmentsBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemEnchantmentsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemEnchantmentsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.enchantments());
	}

	private static class Stub implements ItemEnchantmentsBaseMock
	{
	}
}
