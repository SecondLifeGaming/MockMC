// Auto-generated mechanical sanity test for ProjectileBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ProjectileBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ProjectileBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getShooter());
		assertSafeDefault(mock.getOwnerUniqueId());
	}

	private static class Stub implements ProjectileBaseMock
	{
	}
}
