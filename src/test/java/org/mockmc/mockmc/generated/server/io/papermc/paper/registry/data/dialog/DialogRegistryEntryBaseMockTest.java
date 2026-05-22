// Auto-generated mechanical sanity test for DialogRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.dialog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DialogRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DialogRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.base());
		assertSafeDefault(mock.type());
	}

	private static class Stub implements DialogRegistryEntryBaseMock
	{
	}
}
