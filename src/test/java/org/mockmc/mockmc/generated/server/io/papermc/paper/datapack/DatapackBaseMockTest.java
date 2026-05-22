// Auto-generated mechanical sanity test for DatapackBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datapack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DatapackBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DatapackBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.computeDisplayName());
	}

	private static class Stub implements DatapackBaseMock
	{
	}
}
