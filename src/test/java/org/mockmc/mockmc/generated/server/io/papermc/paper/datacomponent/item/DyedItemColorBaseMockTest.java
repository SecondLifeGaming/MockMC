// Auto-generated mechanical sanity test for DyedItemColorBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DyedItemColorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DyedItemColorBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.color());
	}

	private static class Stub implements DyedItemColorBaseMock
	{
	}
}
