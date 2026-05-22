// Auto-generated mechanical sanity test for PoiSearchResultBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity.poi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PoiSearchResultBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PoiSearchResultBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.location());
		assertSafeDefault(mock.poiType());
	}

	private static class Stub implements PoiSearchResultBaseMock
	{
	}
}
