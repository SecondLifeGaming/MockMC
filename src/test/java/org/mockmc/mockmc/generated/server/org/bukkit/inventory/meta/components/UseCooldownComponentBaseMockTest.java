// Auto-generated mechanical sanity test for UseCooldownComponentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class UseCooldownComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		UseCooldownComponentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCooldownGroup());
	}

	private static class Stub implements UseCooldownComponentBaseMock
	{
	}
}
