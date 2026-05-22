// Auto-generated mechanical sanity test for DatapackRegistrarBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datapack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DatapackRegistrarBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DatapackRegistrarBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDiscoveredPacks());
	}

	private static class Stub implements DatapackRegistrarBaseMock
	{
	}
}
