// Auto-generated mechanical sanity test for SpawnEggMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.SpawnEggMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class SpawnEggMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SpawnEggMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getSpawnedType());
		assertSafeDefault(mock.getSpawnedEntity());
		assertSafeDefault(mock.getCustomSpawnedType());
	}

	private static class Stub implements SpawnEggMetaBaseMock
	{
		@Override
		public SpawnEggMeta clone()
		{
			return null;
		}
	}
}
