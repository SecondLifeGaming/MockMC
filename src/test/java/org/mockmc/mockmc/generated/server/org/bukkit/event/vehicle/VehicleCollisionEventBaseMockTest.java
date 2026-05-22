// Auto-generated mechanical sanity test for VehicleCollisionEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.vehicle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VehicleCollisionEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VehicleCollisionEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHandlers());
	}

	private static class Stub implements VehicleCollisionEventBaseMock
	{
	}
}
