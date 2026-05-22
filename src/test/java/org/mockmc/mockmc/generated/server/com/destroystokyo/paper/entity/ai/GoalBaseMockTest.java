// Auto-generated mechanical sanity test for GoalBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.entity.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.entity.Mob;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class GoalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		GoalBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.getTypes());
	}

	private static class Stub<T extends Mob> implements GoalBaseMock<T>
	{
	}
}
