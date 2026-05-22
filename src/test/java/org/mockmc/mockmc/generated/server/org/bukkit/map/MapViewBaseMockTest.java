// Auto-generated mechanical sanity test for MapViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MapViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MapViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getWorld());
		assertSafeDefault(mock.getScale());
		assertSafeDefault(mock.getRenderers());
	}

	private static class Stub implements MapViewBaseMock
	{
	}
}
