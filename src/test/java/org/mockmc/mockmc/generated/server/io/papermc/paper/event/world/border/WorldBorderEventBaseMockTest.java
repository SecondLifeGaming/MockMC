// Auto-generated mechanical sanity test for WorldBorderEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.event.world.border;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WorldBorderEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WorldBorderEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getWorldBorder());
	}

	private static class Stub implements WorldBorderEventBaseMock
	{
	}
}
