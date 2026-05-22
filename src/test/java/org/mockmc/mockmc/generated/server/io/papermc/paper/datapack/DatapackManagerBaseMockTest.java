// Auto-generated mechanical sanity test for DatapackManagerBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datapack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DatapackManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DatapackManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPacks());
		assertSafeDefault(mock.getEnabledPacks());
	}

	private static class Stub implements DatapackManagerBaseMock
	{
	}
}
