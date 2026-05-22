// Auto-generated mechanical sanity test for ThrownPotionBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ThrownPotionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ThrownPotionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItem());
		assertSafeDefault(mock.getEffects());
		assertSafeDefault(mock.getPotionMeta());
	}

	private static class Stub implements ThrownPotionBaseMock
	{
	}
}
