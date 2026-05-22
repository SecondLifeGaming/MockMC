// Auto-generated mechanical sanity test for EntityEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EntityEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EntityEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntity());
		assertSafeDefault(mock.getEntityType());
	}

	private static class Stub implements EntityEventBaseMock
	{
	}
}
