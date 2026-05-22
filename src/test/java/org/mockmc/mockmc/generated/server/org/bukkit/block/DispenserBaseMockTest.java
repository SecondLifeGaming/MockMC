// Auto-generated mechanical sanity test for DispenserBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DispenserBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DispenserBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBlockProjectileSource());
	}

	private static class Stub implements DispenserBaseMock
	{
	}
}
