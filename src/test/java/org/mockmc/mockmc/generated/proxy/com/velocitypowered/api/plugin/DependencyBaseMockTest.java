// Auto-generated mechanical sanity test for DependencyBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DependencyBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DependencyBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.id());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DependencyBaseMock
	{
	}
}
