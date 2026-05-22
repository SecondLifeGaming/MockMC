// Auto-generated mechanical sanity test for DamageEffectBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.damage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DamageEffectBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DamageEffectBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSound());
	}

	private static class Stub implements DamageEffectBaseMock
	{
	}
}
