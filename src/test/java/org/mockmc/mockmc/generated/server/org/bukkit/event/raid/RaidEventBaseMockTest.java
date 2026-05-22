// Auto-generated mechanical sanity test for RaidEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.raid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RaidEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RaidEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRaid());
	}

	private static class Stub implements RaidEventBaseMock
	{
	}
}
