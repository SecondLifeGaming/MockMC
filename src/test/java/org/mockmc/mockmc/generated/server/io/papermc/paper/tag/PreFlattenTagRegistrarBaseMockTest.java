// Auto-generated mechanical sanity test for PreFlattenTagRegistrarBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PreFlattenTagRegistrarBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PreFlattenTagRegistrarBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAllTags());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.registryKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T> implements PreFlattenTagRegistrarBaseMock<T>
	{
	}
}
