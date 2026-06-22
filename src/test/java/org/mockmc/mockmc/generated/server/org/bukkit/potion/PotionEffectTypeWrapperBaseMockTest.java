// Auto-generated mechanical sanity test for PotionEffectTypeWrapperBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.potion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PotionEffectTypeWrapperBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PotionEffectTypeWrapperBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PotionEffectTypeWrapperBaseMock
	{
	}
}
