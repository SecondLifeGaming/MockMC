// Auto-generated mechanical sanity test for AnvilViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AnvilViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AnvilViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTopInventory());
		assertSafeDefault(mock.getRenameText());
	}

	private static class Stub implements AnvilViewBaseMock
	{
	}
}
