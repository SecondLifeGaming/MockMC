// Auto-generated mechanical sanity test for BukkitBrigadierCommandBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BukkitBrigadierCommandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitBrigadierCommandBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<S extends BukkitBrigadierCommandSource> implements BukkitBrigadierCommandBaseMock<S>
	{
	}
}
