// Auto-generated mechanical sanity test for StonecutterInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StonecutterInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StonecutterInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getInputItem());
	}

	private static class Stub implements StonecutterInventoryBaseMock
	{
	}
}
