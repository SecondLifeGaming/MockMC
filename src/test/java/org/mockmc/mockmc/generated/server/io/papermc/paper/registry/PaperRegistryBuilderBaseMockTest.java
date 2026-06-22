// Auto-generated mechanical sanity test for PaperRegistryBuilderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PaperRegistryBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaperRegistryBuilderBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.build());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<M, T> implements PaperRegistryBuilderBaseMock<M, T>
	{
	}
}
