// Auto-generated mechanical sanity test for PersistentDataTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PersistentDataTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PersistentDataTypeBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getComplexType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getPrimitiveType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<P, C> implements PersistentDataTypeBaseMock<P, C>
	{
	}
}
