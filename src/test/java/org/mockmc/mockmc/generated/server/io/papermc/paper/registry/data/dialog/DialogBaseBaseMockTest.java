// Auto-generated mechanical sanity test for DialogBaseBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DialogBaseBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DialogBaseBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.body());
		assertSafeDefault(mock.inputs());
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.externalTitle());
		assertSafeDefault(mock.afterAction());
	}

	private static class Stub implements DialogBaseBaseMock
	{
	}
}
