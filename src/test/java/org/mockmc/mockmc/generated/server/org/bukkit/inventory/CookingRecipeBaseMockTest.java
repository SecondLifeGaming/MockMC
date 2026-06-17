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
		try
		{
			assertSafeDefault(mock.getKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getResult());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getInput());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getInputChoice());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getCategory());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getGroup());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T extends CookingRecipe> implements CookingRecipeBaseMock<T>
	{
	}
}
