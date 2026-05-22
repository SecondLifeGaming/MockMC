// Auto-generated mechanical sanity test for RecipeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RecipeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RecipeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
	}

	private static class Stub implements RecipeBaseMock
	{
	}
}
