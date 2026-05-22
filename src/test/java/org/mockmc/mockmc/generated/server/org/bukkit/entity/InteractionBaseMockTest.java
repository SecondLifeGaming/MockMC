// Auto-generated mechanical sanity test for InteractionBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class InteractionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		InteractionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLastInteraction());
		assertSafeDefault(mock.getLastAttack());
	}

	private static class Stub implements InteractionBaseMock
	{
	}
}
