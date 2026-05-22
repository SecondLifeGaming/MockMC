// Auto-generated mechanical sanity test for ItemContainerContentsBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemContainerContentsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemContainerContentsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.contents());
	}

	private static class Stub implements ItemContainerContentsBaseMock
	{
	}
}
