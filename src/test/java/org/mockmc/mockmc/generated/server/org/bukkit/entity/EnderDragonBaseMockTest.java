// Auto-generated mechanical sanity test for EnderDragonBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EnderDragonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnderDragonBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPhase());
		assertSafeDefault(mock.getPodium());
		assertSafeDefault(mock.getDragonBattle());
	}

	private static class Stub implements EnderDragonBaseMock
	{
	}
}
