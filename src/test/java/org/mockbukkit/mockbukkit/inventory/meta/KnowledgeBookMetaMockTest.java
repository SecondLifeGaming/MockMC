package org.mockbukkit.mockbukkit.inventory.meta;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockbukkit.mockbukkit.MockBukkitExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockBukkitExtension.class)
class KnowledgeBookMetaMockTest
{

	private static final int MAX_RECIPES = 32767;

	@NotNull
	@SuppressWarnings("deprecation")
	private NamespacedKey getRandomKey()
	{
		return NamespacedKey.randomKey();
	}

	@Test
	void testRecipesDefaultFalse()
	{
		KnowledgeBookMeta meta = new KnowledgeBookMetaMock();
		assertFalse(meta.hasRecipes());
	}

	@Test
	void testAddRecipe()
	{
		KnowledgeBookMeta meta = new KnowledgeBookMetaMock();
		NamespacedKey key = getRandomKey();

		assertFalse(meta.hasRecipes());
		meta.addRecipe(key);
		assertTrue(meta.hasRecipes());
	}

	@Test
	void testAddNullRecipeAndFail()
	{
		KnowledgeBookMeta meta = new KnowledgeBookMetaMock();
		List<NamespacedKey> recipes = Arrays.asList(null, null, null);

		assertFalse(meta.hasRecipes());
		meta.setRecipes(recipes);
		assertFalse(meta.hasRecipes());
	}

	@Test
	void testSetRecipes()
	{
		KnowledgeBookMeta meta = new KnowledgeBookMetaMock();
		List<NamespacedKey> recipes = List.of(getRandomKey(), getRandomKey());

		assertFalse(meta.hasRecipes());
		meta.setRecipes(recipes);
		assertTrue(meta.hasRecipes());
	}

	@Test
	void testGetRecipes()
	{
		KnowledgeBookMeta meta = new KnowledgeBookMetaMock();
		List<NamespacedKey> recipes = List.of(getRandomKey(), getRandomKey());
		meta.setRecipes(recipes);

		assertEquals(recipes, meta.getRecipes());
	}

	@Test
	void testTooManyRecipes()
	{
		KnowledgeBookMeta meta = new KnowledgeBookMetaMock();

		for (int i = 0; i < MAX_RECIPES + 50; i++)
		{
			meta.addRecipe(getRandomKey());
		}

		assertEquals(MAX_RECIPES, meta.getRecipes().size());
	}

	@Test
	void testEquals()
	{
		KnowledgeBookMeta meta = new KnowledgeBookMetaMock();
		assertEquals(meta, meta);
		assertNotEquals(new ItemMetaMock(), meta);
		assertNotEquals(meta, new ItemMetaMock());

		KnowledgeBookMeta meta2 = new KnowledgeBookMetaMock();
		assertEquals(meta, meta2);

		NamespacedKey recipe = getRandomKey();

		meta.addRecipe(recipe);
		assertNotEquals(meta, meta2);

		meta2.addRecipe(recipe);
		assertEquals(meta, meta2);
	}

}
