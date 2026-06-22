// Auto-generated mechanical sanity test for RegistryComposeEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class RegistryComposeEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryComposeEventBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.registry());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T, B extends RegistryBuilder<T>> implements RegistryComposeEventBaseMock<T, B>
	{
	}
}
