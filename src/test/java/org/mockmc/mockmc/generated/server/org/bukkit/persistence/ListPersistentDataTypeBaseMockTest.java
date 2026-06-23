// Auto-generated mechanical sanity test for ListPersistentDataTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ListPersistentDataTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ListPersistentDataTypeBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.elementType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<P, C> implements ListPersistentDataTypeBaseMock<P, C>
	{
	}
}
