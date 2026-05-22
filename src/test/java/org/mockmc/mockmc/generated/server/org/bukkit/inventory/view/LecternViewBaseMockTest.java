// Auto-generated mechanical sanity test for LecternViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LecternViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LecternViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTopInventory());
	}

	private static class Stub implements LecternViewBaseMock
	{
	}
}
