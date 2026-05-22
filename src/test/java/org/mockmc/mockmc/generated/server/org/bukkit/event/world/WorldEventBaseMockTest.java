// Auto-generated mechanical sanity test for WorldEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.world;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WorldEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WorldEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getWorld());
	}

	private static class Stub implements WorldEventBaseMock
	{
	}
}
