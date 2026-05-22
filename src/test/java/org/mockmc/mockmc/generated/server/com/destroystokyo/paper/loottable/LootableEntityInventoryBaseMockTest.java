// Auto-generated mechanical sanity test for LootableEntityInventoryBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.loottable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LootableEntityInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LootableEntityInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntity());
	}

	private static class Stub implements LootableEntityInventoryBaseMock
	{
	}
}
