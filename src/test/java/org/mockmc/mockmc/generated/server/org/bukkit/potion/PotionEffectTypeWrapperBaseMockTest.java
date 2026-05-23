// Auto-generated mechanical sanity test for PotionEffectTypeWrapperBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.potion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
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
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PotionEffectTypeWrapperBaseMock
	{
	}
}
