// Auto-generated mechanical sanity test for HolderableBaseBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HolderableBaseBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HolderableBaseBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toString());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getHandle());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getHolder());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<M> implements HolderableBaseBaseMock<M>
	{
	}
}
