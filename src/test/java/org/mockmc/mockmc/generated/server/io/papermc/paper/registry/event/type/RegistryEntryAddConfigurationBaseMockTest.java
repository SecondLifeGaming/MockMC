// Auto-generated mechanical sanity test for RegistryEntryAddConfigurationBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event.type;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryEntryAddConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryEntryAddConfigurationBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.monitor());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T> implements RegistryEntryAddConfigurationBaseMock<T>
	{
	}
}
