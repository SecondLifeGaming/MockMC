// Auto-generated mechanical sanity test for VillagerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VillagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VillagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getProfession());
		assertSafeDefault(mock.zombify());
		assertSafeDefault(mock.getVillagerType());
		assertSafeDefault(mock.getReputations());
	}

	private static class Stub implements VillagerBaseMock
	{
	}
}
