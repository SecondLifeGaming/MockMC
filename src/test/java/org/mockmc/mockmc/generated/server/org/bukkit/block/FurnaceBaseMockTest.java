// Auto-generated mechanical sanity test for FurnaceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FurnaceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FurnaceBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getSnapshotInventory());
		assertSafeDefault(mock.getRecipesUsed());
	}

	private static class Stub implements FurnaceBaseMock
	{
	}
}
