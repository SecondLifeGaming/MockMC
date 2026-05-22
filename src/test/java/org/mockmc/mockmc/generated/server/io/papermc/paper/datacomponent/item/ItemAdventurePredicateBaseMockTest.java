// Auto-generated mechanical sanity test for ItemAdventurePredicateBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ItemAdventurePredicateBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemAdventurePredicateBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.predicates());
	}

	private static class Stub implements ItemAdventurePredicateBaseMock
	{
	}
}
