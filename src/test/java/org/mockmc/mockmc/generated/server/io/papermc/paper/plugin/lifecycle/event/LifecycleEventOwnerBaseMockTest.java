// Auto-generated mechanical sanity test for LifecycleEventOwnerBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LifecycleEventOwnerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LifecycleEventOwnerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPluginMeta());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements LifecycleEventOwnerBaseMock
	{
	}
}
