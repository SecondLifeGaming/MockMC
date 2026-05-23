// Auto-generated mechanical sanity test for ShulkerBulletBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ShulkerBulletBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ShulkerBulletBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getTargetDelta());
		assertSafeDefault(mock.getCurrentMovementDirection());
	}

	private static class Stub implements ShulkerBulletBaseMock
	{
	}
}
