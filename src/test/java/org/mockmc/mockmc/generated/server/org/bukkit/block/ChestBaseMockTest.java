// Auto-generated mechanical sanity test for ChestBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChestBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChestBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBlockInventory());
	}

	private static class Stub implements ChestBaseMock
	{
	}
}
