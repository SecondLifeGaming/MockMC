// Auto-generated mechanical sanity test for DolphinBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DolphinBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DolphinBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTreasureLocation());
	}

	private static class Stub implements DolphinBaseMock
	{
	}
}
