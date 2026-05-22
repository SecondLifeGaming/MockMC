// Auto-generated mechanical sanity test for WorldBorderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WorldBorderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WorldBorderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getWorld());
		assertSafeDefault(mock.getCenter());
	}

	private static class Stub implements WorldBorderBaseMock
	{
	}
}
