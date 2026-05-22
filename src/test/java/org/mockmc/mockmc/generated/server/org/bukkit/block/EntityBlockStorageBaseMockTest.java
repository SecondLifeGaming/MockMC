// Auto-generated mechanical sanity test for EntityBlockStorageBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.entity.Entity;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EntityBlockStorageBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EntityBlockStorageBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.releaseEntities());
	}

	private static class Stub<T extends Entity> implements EntityBlockStorageBaseMock<T>
	{
	}
}
