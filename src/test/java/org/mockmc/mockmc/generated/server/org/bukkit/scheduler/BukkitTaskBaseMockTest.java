// Auto-generated mechanical sanity test for BukkitTaskBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BukkitTaskBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitTaskBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOwner());
	}

	private static class Stub implements BukkitTaskBaseMock
	{
	}
}
