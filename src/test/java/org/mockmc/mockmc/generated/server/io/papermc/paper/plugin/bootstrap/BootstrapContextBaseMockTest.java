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
		try
		{
			assertSafeDefault(mock.getLifecycleManager());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BootstrapContextBaseMock
	{
	}
}
