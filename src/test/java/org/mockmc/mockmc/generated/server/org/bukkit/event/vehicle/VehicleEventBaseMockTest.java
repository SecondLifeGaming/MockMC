// Auto-generated mechanical sanity test for VehicleEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.vehicle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VehicleEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VehicleEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVehicle());
	}

	private static class Stub implements VehicleEventBaseMock
	{
	}
}
