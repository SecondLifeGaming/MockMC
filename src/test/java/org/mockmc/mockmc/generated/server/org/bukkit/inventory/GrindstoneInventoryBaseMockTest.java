// Auto-generated mechanical sanity test for GrindstoneInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class GrindstoneInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		GrindstoneInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getUpperItem());
		assertSafeDefault(mock.getLowerItem());
	}

	private static class Stub implements GrindstoneInventoryBaseMock
	{
	}
}
