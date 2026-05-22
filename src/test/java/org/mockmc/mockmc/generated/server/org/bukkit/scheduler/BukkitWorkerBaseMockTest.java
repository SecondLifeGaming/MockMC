// Auto-generated mechanical sanity test for BukkitWorkerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BukkitWorkerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BukkitWorkerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOwner());
		assertSafeDefault(mock.getThread());
	}

	private static class Stub implements BukkitWorkerBaseMock
	{
	}
}
