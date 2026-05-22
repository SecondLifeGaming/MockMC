// Auto-generated mechanical sanity test for ItemFactoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemFactoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemFactoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDefaultLeatherColor());
	}

	private static class Stub implements ItemFactoryBaseMock
	{
	}
}
