// Auto-generated mechanical sanity test for PlainMessageDialogBodyBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.body;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlainMessageDialogBodyBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlainMessageDialogBodyBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.contents());
	}

	private static class Stub implements PlainMessageDialogBodyBaseMock
	{
	}
}
