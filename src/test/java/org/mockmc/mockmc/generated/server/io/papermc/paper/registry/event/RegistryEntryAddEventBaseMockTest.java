// Auto-generated mechanical sanity test for RegistryEntryAddEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class RegistryEntryAddEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryEntryAddEventBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.builder());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.key());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T, B extends RegistryBuilder<T>> implements RegistryEntryAddEventBaseMock<T, B>
	{
	}
}
