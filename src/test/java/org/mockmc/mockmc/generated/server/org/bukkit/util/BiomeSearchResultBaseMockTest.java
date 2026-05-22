// Auto-generated mechanical sanity test for BiomeSearchResultBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BiomeSearchResultBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BiomeSearchResultBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getBiome());
	}

	private static class Stub implements BiomeSearchResultBaseMock
	{
	}
}
