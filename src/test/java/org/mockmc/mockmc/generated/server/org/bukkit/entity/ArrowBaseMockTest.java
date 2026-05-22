// Auto-generated mechanical sanity test for ArrowBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ArrowBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ArrowBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getBasePotionData());
		assertSafeDefault(mock.getBasePotionType());
		assertSafeDefault(mock.getCustomEffects());
	}

	private static class Stub implements ArrowBaseMock
	{
	}
}
