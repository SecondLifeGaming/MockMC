// Auto-generated mechanical sanity test for ItemTagTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.tags;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ItemTagTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemTagTypeBaseMock<?, ?> mock = new Stub<>();
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

	private static class Stub<T, Z> implements ItemTagTypeBaseMock<T, Z>
	{
	}
}
