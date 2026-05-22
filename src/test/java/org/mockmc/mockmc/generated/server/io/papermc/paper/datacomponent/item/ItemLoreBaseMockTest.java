// Auto-generated mechanical sanity test for ItemLoreBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemLoreBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemLoreBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.lines());
		assertSafeDefault(mock.styledLines());
	}

	private static class Stub implements ItemLoreBaseMock
	{
	}
}
