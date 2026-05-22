// Auto-generated mechanical sanity test for ItemFrameBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemFrameBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemFrameBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRotation());
		assertSafeDefault(mock.getItem());
	}

	private static class Stub implements ItemFrameBaseMock
	{
	}
}
