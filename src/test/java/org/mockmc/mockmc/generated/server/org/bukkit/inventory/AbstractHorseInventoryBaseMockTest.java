// Auto-generated mechanical sanity test for AbstractHorseInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AbstractHorseInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractHorseInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSaddle());
	}

	private static class Stub implements AbstractHorseInventoryBaseMock
	{
	}
}
