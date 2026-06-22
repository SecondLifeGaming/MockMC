// Auto-generated mechanical sanity test for CowVariantRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class CowVariantRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CowVariantRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.model());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.clientTextureAsset());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.babyClientTextureAsset());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements CowVariantRegistryEntryBaseMock
	{
	}
}
