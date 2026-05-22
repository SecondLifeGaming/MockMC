// Auto-generated mechanical sanity test for RaidBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RaidBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RaidBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getBossBar());
		assertSafeDefault(mock.getStatus());
		assertSafeDefault(mock.getHeroes());
		assertSafeDefault(mock.getRaiders());
	}

	private static class Stub implements RaidBaseMock
	{
	}
}
