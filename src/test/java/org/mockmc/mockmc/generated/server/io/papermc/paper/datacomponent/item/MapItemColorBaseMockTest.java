// Auto-generated mechanical sanity test for MapItemColorBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MapItemColorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MapItemColorBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.color());
	}

	private static class Stub implements MapItemColorBaseMock
	{
	}
}
