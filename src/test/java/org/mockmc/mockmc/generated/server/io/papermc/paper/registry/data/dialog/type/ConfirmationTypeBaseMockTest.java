// Auto-generated mechanical sanity test for ConfirmationTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.type;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConfirmationTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConfirmationTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.yesButton());
		assertSafeDefault(mock.noButton());
	}

	private static class Stub implements ConfirmationTypeBaseMock
	{
	}
}
