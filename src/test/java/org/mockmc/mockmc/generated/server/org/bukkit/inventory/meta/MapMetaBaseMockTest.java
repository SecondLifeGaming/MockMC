// Auto-generated mechanical sanity test for MapMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.MapMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class MapMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MapMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getLocationName());
		assertSafeDefault(mock.getMapView());
	}

	private static class Stub implements MapMetaBaseMock
	{
		@Override
		public MapMeta clone()
		{
			return null;
		}
	}
}
