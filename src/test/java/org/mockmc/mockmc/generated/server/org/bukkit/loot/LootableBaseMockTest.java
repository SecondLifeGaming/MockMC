// Auto-generated mechanical sanity test for LootableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.loot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LootableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LootableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLootTable());
	}

	private static class Stub implements LootableBaseMock
	{
	}
}
