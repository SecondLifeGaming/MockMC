// Auto-generated mechanical sanity test for TropicalFishBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TropicalFishBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TropicalFishBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPattern());
		assertSafeDefault(mock.getPatternColor());
		assertSafeDefault(mock.getBodyColor());
	}

	private static class Stub implements TropicalFishBaseMock
	{
	}
}
