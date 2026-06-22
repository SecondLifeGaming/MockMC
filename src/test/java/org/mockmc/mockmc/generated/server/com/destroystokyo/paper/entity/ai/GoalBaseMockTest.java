// Auto-generated mechanical sanity test for GoalBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.entity.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.entity.Mob;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class GoalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		GoalBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getTypes());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T extends Mob> implements GoalBaseMock<T>
	{
	}
}
