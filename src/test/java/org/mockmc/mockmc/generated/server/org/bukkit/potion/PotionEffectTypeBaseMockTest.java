// Auto-generated mechanical sanity test for PotionEffectTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.potion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class PotionEffectTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PotionEffectTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getEffectCategory());
		assertSafeDefault(mock.getCategory());
		assertSafeDefault(mock.getEffectAttributes());
	}

	private static class Stub implements PotionEffectTypeBaseMock
	{
	}
}
