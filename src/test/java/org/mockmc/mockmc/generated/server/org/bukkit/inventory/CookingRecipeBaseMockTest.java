// Auto-generated mechanical sanity test for CookingRecipeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.CookingRecipe;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class CookingRecipeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CookingRecipeBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getInput());
		assertSafeDefault(mock.getInputChoice());
		assertSafeDefault(mock.getGroup());
		assertSafeDefault(mock.getCategory());
	}

	private static class Stub<T extends CookingRecipe> implements CookingRecipeBaseMock<T>
	{
	}
}
