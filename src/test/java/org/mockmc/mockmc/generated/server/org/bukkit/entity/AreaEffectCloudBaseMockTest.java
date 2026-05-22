// Auto-generated mechanical sanity test for AreaEffectCloudBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class AreaEffectCloudBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AreaEffectCloudBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getSource());
		assertSafeDefault(mock.getOwnerUniqueId());
		assertSafeDefault(mock.getParticle());
		assertSafeDefault(mock.getBasePotionData());
		assertSafeDefault(mock.getBasePotionType());
		assertSafeDefault(mock.getCustomEffects());
	}

	private static class Stub implements AreaEffectCloudBaseMock
	{
	}
}
