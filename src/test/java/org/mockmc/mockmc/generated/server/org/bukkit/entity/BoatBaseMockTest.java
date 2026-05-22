// Auto-generated mechanical sanity test for BoatBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BoatBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BoatBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getStatus());
		assertSafeDefault(mock.getWoodType());
		assertSafeDefault(mock.getBoatType());
		assertSafeDefault(mock.getBoatMaterial());
	}

	private static class Stub implements BoatBaseMock
	{
	}
}
