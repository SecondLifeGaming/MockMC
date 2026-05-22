// Auto-generated mechanical sanity test for AdvancementProgressBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.advancement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AdvancementProgressBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AdvancementProgressBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAdvancement());
		assertSafeDefault(mock.getRemainingCriteria());
		assertSafeDefault(mock.getAwardedCriteria());
	}

	private static class Stub implements AdvancementProgressBaseMock
	{
	}
}
