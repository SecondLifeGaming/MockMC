// Auto-generated mechanical sanity test for MapCanvasBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MapCanvasBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MapCanvasBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMapView());
		assertSafeDefault(mock.getCursors());
	}

	private static class Stub implements MapCanvasBaseMock
	{
	}
}
