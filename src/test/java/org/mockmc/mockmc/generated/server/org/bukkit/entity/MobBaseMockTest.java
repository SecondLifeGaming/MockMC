// Auto-generated mechanical sanity test for MobBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MobBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MobBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getEquipment());
		assertSafeDefault(mock.getDespawnInPeacefulOverride());
		assertSafeDefault(mock.getPathfinder());
		assertSafeDefault(mock.getAmbientSound());
	}

	private static class Stub implements MobBaseMock
	{
	}
}
