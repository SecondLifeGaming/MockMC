// Auto-generated mechanical sanity test for CustomItemTagContainerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.tags;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class CustomItemTagContainerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CustomItemTagContainerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAdapterContext());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements CustomItemTagContainerBaseMock
	{
	}
}
