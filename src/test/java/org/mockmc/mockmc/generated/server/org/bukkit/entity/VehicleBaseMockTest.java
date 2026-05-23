// Auto-generated mechanical sanity test for VehicleBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VehicleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VehicleBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getVelocity());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements VehicleBaseMock
	{
	}
}
