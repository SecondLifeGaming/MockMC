// Auto-generated mechanical sanity test for BootstrapContextBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.bootstrap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BootstrapContextBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BootstrapContextBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLifecycleManager());
	}

	private static class Stub implements BootstrapContextBaseMock
	{
	}
}
