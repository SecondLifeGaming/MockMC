// Auto-generated mechanical sanity test for ActionButtonBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ActionButtonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ActionButtonBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.action());
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.tooltip());
	}

	private static class Stub implements ActionButtonBaseMock
	{
	}
}
