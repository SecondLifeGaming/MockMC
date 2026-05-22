// Auto-generated mechanical sanity test for BiomeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BiomeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BiomeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.translationKey());
	}

	private static class Stub implements BiomeBaseMock
	{
	}
}
