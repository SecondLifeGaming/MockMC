// Auto-generated mechanical sanity test for TextDialogInputBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TextDialogInputBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TextDialogInputBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.multiline());
		assertSafeDefault(mock.initial());
	}

	private static class Stub implements TextDialogInputBaseMock
	{
	}
}
