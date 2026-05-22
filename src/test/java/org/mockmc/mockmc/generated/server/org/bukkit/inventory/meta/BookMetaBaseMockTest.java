// Auto-generated mechanical sanity test for BookMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.BookMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BookMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BookMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.author());
		assertSafeDefault(mock.spigot());
		assertSafeDefault(mock.getTitle());
		assertSafeDefault(mock.getAuthor());
		assertSafeDefault(mock.toBuilder());
		assertSafeDefault(mock.getGeneration());
		assertSafeDefault(mock.getPages());
	}

	private static class Stub implements BookMetaBaseMock
	{
		@Override
		public BookMeta clone()
		{
			return null;
		}
	}
}
