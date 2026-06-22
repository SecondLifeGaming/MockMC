// Auto-generated mechanical sanity test for RegistryEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class RegistryEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryEventBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.registryKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T> implements RegistryEventBaseMock<T>
	{
	}
}
