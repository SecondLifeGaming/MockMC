// Auto-generated mechanical sanity test for BukkitBrigadierCommandSourceBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BukkitBrigadierCommandSourceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitBrigadierCommandSourceBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBukkitEntity());
		assertSafeDefault(mock.getBukkitWorld());
		assertSafeDefault(mock.getBukkitLocation());
		assertSafeDefault(mock.getBukkitSender());
	}

	private static class Stub implements BukkitBrigadierCommandSourceBaseMock
	{
	}
}
