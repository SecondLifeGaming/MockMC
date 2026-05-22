// Auto-generated mechanical sanity test for ItemDisplayBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemDisplayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemDisplayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItemStack());
		assertSafeDefault(mock.getItemDisplayTransform());
	}

	private static class Stub implements ItemDisplayBaseMock
	{
	}
}
