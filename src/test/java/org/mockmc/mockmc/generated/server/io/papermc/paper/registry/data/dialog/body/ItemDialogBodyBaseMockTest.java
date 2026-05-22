// Auto-generated mechanical sanity test for ItemDialogBodyBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.body;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemDialogBodyBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemDialogBodyBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.item());
	}

	private static class Stub implements ItemDialogBodyBaseMock
	{
	}
}
