// Auto-generated mechanical sanity test for NumberRangeDialogInputBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class NumberRangeDialogInputBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		NumberRangeDialogInputBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.step());
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.labelFormat());
		assertSafeDefault(mock.initial());
	}

	private static class Stub implements NumberRangeDialogInputBaseMock
	{
	}
}
