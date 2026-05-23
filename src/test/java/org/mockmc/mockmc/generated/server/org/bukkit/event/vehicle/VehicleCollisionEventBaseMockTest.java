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
		try
		{
			assertSafeDefault(mock.getHandlers());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements VehicleCollisionEventBaseMock
	{
	}
}
