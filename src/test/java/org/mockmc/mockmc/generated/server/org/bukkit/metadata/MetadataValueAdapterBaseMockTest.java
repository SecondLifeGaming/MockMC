// Auto-generated mechanical sanity test for MetadataValueAdapterBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.metadata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class MetadataValueAdapterBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MetadataValueAdapterBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getOwningPlugin());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.asString());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements MetadataValueAdapterBaseMock
	{
	}
}
