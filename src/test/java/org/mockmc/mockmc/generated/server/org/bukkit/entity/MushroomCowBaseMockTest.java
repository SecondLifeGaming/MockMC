// Auto-generated mechanical sanity test for MushroomCowBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class MushroomCowBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MushroomCowBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
		assertSafeDefault(mock.getEffectsForNextStew());
		assertSafeDefault(mock.getStewEffectType());
		assertSafeDefault(mock.getStewEffects());
	}

	private static class Stub implements MushroomCowBaseMock
	{
	}
}
