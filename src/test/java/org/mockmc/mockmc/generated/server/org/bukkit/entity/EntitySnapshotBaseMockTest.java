// Auto-generated mechanical sanity test for EntitySnapshotBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EntitySnapshotBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EntitySnapshotBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAsString());
		assertSafeDefault(mock.getEntityType());
	}

	private static class Stub implements EntitySnapshotBaseMock
	{
	}
}
