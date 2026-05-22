// Auto-generated mechanical sanity test for SkullMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.SkullMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class SkullMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SkullMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getOwner());
		assertSafeDefault(mock.getPlayerProfile());
		assertSafeDefault(mock.getOwningPlayer());
		assertSafeDefault(mock.getOwnerProfile());
		assertSafeDefault(mock.getNoteBlockSound());
	}

	private static class Stub implements SkullMetaBaseMock
	{
		@Override
		public SkullMeta clone()
		{
			return null;
		}
	}
}
