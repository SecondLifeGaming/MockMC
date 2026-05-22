// Auto-generated mechanical sanity test for LoomViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LoomViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LoomViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTopInventory());
		assertSafeDefault(mock.getSelectablePatterns());
	}

	private static class Stub implements LoomViewBaseMock
	{
	}
}
