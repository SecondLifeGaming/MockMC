// Auto-generated mechanical sanity test for MenuTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MenuTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MenuTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.typed());
		assertSafeDefault(mock.getInventoryViewClass());
	}

	private static class Stub implements MenuTypeBaseMock
	{
	}
}
