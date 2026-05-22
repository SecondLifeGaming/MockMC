// Auto-generated mechanical sanity test for DamageTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.damage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DamageTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DamageTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDamageScaling());
		assertSafeDefault(mock.getDamageEffect());
		assertSafeDefault(mock.getDeathMessageType());
		assertSafeDefault(mock.getTranslationKey());
	}

	private static class Stub implements DamageTypeBaseMock
	{
	}
}
