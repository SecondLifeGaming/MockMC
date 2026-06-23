// Auto-generated mechanical sanity test for PersistentDataAdapterContextBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PersistentDataAdapterContextBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PersistentDataAdapterContextBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.newPersistentDataContainer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PersistentDataAdapterContextBaseMock
	{
	}
}
