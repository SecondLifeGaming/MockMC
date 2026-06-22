// Auto-generated mechanical sanity test for FrogVariantRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class FrogVariantRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FrogVariantRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.clientTextureAsset());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FrogVariantRegistryEntryBaseMock
	{
	}
}
