// Auto-generated mechanical sanity test for BooleanDialogInputBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BooleanDialogInputBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BooleanDialogInputBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.onTrue());
		assertSafeDefault(mock.onFalse());
	}

	private static class Stub implements BooleanDialogInputBaseMock
	{
	}
}
