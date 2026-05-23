// Auto-generated mechanical sanity test for RegistryValueSetBuilderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.set;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryValueSetBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryValueSetBuilderBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.build());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<API, ENTRY_BUILDER extends RegistryBuilder<API>>
			implements
				RegistryValueSetBuilderBaseMock<API, ENTRY_BUILDER>
	{
	}
}
