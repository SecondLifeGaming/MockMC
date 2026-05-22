// Auto-generated mechanical sanity test for ZombieVillagerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ZombieVillagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ZombieVillagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVillagerProfession());
		assertSafeDefault(mock.getVillagerType());
		assertSafeDefault(mock.getConversionPlayer());
	}

	private static class Stub implements ZombieVillagerBaseMock
	{
	}
}
