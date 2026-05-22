package org.mockmc.metaminer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

class GenerateMocksTest
{

	@BeforeAll
	static void setupBukkit()
	{
		Server mockServer = Mockito.mock(Server.class);

		java.util.logging.Logger mockLogger = java.util.logging.Logger.getLogger("mock");
		Mockito.when(mockServer.getLogger()).thenReturn(mockLogger);
		Mockito.when(mockServer.getName()).thenReturn("MockServer");
		Mockito.when(mockServer.getVersion()).thenReturn("1.21.1");
		Mockito.when(mockServer.getBukkitVersion()).thenReturn("1.21.1-R0.1-SNAPSHOT");

		Bukkit.setServer(mockServer);

		World mockWorld = Mockito.mock(World.class);
		Mockito.when(mockServer.getWorlds()).thenReturn(List.of(mockWorld));
		
		Mockito.when(mockWorld.getGameRules()).thenReturn(new String[]{"minecraft:dummy_rule", "minecraft:dummy_rule2", "minecraft:dummy_rule3"});
		Mockito.when(mockWorld.getGameRuleValue(Mockito.<org.bukkit.GameRule<Boolean>>any())).thenReturn(true);

		java.util.List<org.bukkit.inventory.Recipe> mockRecipes = java.util.List.of(
				Mockito.mock(org.bukkit.inventory.ShapelessRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.ShapedRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.TransmuteRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.ComplexRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.StonecuttingRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.FurnaceRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.BlastingRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.SmokingRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.CampfireRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.SmithingTransformRecipe.class, Mockito.RETURNS_DEEP_STUBS),
				Mockito.mock(org.bukkit.inventory.SmithingTrimRecipe.class, Mockito.RETURNS_DEEP_STUBS));

		for (org.bukkit.inventory.Recipe r : mockRecipes)
		{
			if (r instanceof org.bukkit.Keyed keyed)
			{
				Mockito.when(keyed.getKey()).thenReturn(org.bukkit.NamespacedKey.minecraft("dummy_"
						+ r.getClass().getSimpleName().toLowerCase().replace("$$mockito", "").split("\\$")[0]));
			}
		}
		Mockito.when(mockServer.recipeIterator()).thenReturn(mockRecipes.iterator());

		BlockData mockBlockData = Mockito.mock(BlockData.class);
		Mockito.when(mockBlockData.getAsString(Mockito.anyBoolean())).thenReturn("minecraft:stone[foo=bar]");
		Mockito.when(mockServer.createBlockData(Mockito.any(Material.class))).thenReturn(mockBlockData);

		ItemFactory mockItemFactory = Mockito.mock(ItemFactory.class);
		Mockito.when(mockServer.getItemFactory()).thenReturn(mockItemFactory);
		ItemMeta mockMeta = Mockito.mock(ItemMeta.class);
		Mockito.when(mockItemFactory.getItemMeta(Mockito.any(Material.class))).thenReturn(mockMeta);
	}

	@Test
	void testFullGenerationCycle(@org.junit.jupiter.api.io.TempDir File tempDir) throws Exception
	{
		// Run the full standalone cycle to cover all generators using the temp directory
		StandaloneRunner.main(new String[]
		{ tempDir.getAbsolutePath() });

		File generatedDir = new File(tempDir, "src/main/java/org/mockmc/mockmc/generated");
		Assertions.assertTrue(generatedDir.exists() && generatedDir.isDirectory(), "Generated directory should exist");

		File coverageReport = new File(tempDir, "COVERAGE.md");
		Assertions.assertTrue(coverageReport.exists(), "Coverage report should be generated");
	}
}
