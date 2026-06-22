// Auto-generated mechanical sanity test for RequiresSpigotInitializationBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.configuration.legacy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class RequiresSpigotInitializationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RequiresSpigotInitializationBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.value());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements RequiresSpigotInitializationBaseMock
	{
	}
}
