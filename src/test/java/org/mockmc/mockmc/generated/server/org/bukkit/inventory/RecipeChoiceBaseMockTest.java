// Auto-generated mechanical sanity test for RecipeChoiceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.RecipeChoice;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class RecipeChoiceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RecipeChoiceBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getItemStack());
	}

	private static class Stub implements RecipeChoiceBaseMock
	{
		@Override
		public RecipeChoice clone()
		{
			return null;
		}
	}
}
