// Auto-generated mechanical sanity test for RegistryHolderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryHolderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryHolderBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.get());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<B extends Keyed> implements RegistryHolderBaseMock<B>
	{
	}
}
