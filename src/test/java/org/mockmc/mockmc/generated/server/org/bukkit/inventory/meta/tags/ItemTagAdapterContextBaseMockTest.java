// Auto-generated mechanical sanity test for ItemTagAdapterContextBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.tags;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ItemTagAdapterContextBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemTagAdapterContextBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.newTagContainer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ItemTagAdapterContextBaseMock
	{
	}
}
