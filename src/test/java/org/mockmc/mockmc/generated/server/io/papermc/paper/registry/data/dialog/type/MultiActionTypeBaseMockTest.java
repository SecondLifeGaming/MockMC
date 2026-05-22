// Auto-generated mechanical sanity test for MultiActionTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog.type;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MultiActionTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MultiActionTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.actions());
		assertSafeDefault(mock.exitAction());
	}

	private static class Stub implements MultiActionTypeBaseMock
	{
	}
}
