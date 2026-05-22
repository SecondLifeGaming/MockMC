// Auto-generated mechanical sanity test for PotionMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.PotionMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class PotionMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PotionMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getCustomName());
		assertSafeDefault(mock.getBasePotionData());
		assertSafeDefault(mock.getBasePotionType());
		assertSafeDefault(mock.getCustomEffects());
		assertSafeDefault(mock.getAllEffects());
		assertSafeDefault(mock.computeEffectiveColor());
		assertSafeDefault(mock.getCustomPotionName());
	}

	private static class Stub implements PotionMetaBaseMock
	{
		@Override
		public PotionMeta clone()
		{
			return null;
		}
	}
}
