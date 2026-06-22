// Auto-generated mechanical sanity test for KnowledgeBookMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class KnowledgeBookMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		KnowledgeBookMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.clone());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getRecipes());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements KnowledgeBookMetaBaseMock
	{
		@Override
		public KnowledgeBookMeta clone()
		{
			return null;
		}
	}
}
