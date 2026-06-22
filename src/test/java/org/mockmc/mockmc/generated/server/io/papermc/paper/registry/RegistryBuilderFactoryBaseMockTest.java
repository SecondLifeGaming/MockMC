// Auto-generated mechanical sanity test for RegistryBuilderFactoryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class RegistryBuilderFactoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryBuilderFactoryBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.empty());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T, B extends RegistryBuilder<T>> implements RegistryBuilderFactoryBaseMock<T, B>
	{
	}
}
