// Auto-generated mechanical sanity test for SaddledMountInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SaddledMountInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SaddledMountInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSaddle());
	}

	private static class Stub implements SaddledMountInventoryBaseMock
	{
	}
}
