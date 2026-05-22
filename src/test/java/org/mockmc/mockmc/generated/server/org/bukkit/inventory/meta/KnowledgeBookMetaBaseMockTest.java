// Auto-generated mechanical sanity test for KnowledgeBookMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class KnowledgeBookMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		KnowledgeBookMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getRecipes());
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
