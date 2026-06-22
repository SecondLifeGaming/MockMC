// Auto-generated mechanical sanity test for VanillaGoalBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.entity.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.entity.Mob;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class VanillaGoalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VanillaGoalBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<T extends Mob> implements VanillaGoalBaseMock<T>
	{
	}
}
