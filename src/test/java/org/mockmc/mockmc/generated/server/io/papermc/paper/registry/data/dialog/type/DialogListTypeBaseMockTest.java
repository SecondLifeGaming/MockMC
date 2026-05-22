// Auto-generated mechanical sanity test for DialogListTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.type;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DialogListTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DialogListTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.exitAction());
		assertSafeDefault(mock.dialogs());
	}

	private static class Stub implements DialogListTypeBaseMock
	{
	}
}
