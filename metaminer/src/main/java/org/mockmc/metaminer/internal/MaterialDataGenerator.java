package org.mockmc.metaminer.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Brushable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Hatchable;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.block.data.type.CreakingHeart;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.block.data.type.TurtleEgg;
import org.jetbrains.annotations.NotNull;
import org.mockmc.metaminer.DataGenerator;
import org.mockmc.metaminer.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaterialDataGenerator implements DataGenerator
{

	private final File dataFolder;
	private static final Pattern BLOCK_DATA_PATTERN = Pattern.compile("\\[(.*)]");

	public MaterialDataGenerator(File parentDataFolder)
	{
		this.dataFolder = new File(parentDataFolder, "materials");
	}

	@Override
	public void generateData() throws IOException
	{
		JsonObject json = createJsonObject();
		JsonUtil.dump(json, new File(dataFolder, "material_data.json"));
	}

	private static @NotNull JsonObject createJsonObject()
	{
		JsonObject json = new JsonObject();
		Material[] materials = Material.values();
		Arrays.sort(materials, Comparator.comparing(Enum::name));

		for (Material material : materials)
		{
			processMaterial(json, material);
		}
		return json;
	}

	private static void processMaterial(JsonObject json, Material material)
	{
		try
		{
			BlockData data = material.createBlockData();
			String dataString = data.getAsString(false);
			Matcher matcher = BLOCK_DATA_PATTERN.matcher(dataString);
			if (!matcher.find())
			{
				json.add(material.key().toString(), new JsonObject());
				return;
			}

			JsonObject obj = new JsonObject();
			obj.add("allowedStates", extractAllowedStates(data));
			obj.add("defaultStates", parseDefaultStates(matcher.group(1)));

			json.add(material.key().toString(), obj);
		}
		catch (Exception _)
		{
			// Material does not support BlockData or other extraction error
		}
	}

	private static JsonObject extractAllowedStates(BlockData data)
	{
		JsonObject allowedStates = new JsonObject();
		extractCustomBlockDataProperties(data, allowedStates);
		return allowedStates;
	}

	private static JsonObject parseDefaultStates(String statesString)
	{
		JsonObject defaultStates = new JsonObject();
		String[] states = statesString.split(",");
		for (String state : states)
		{
			String[] stateSplit = state.split("=");
			String key = stateSplit[0].trim();
			String value = stateSplit[1].trim();
			addStateToResult(defaultStates, key, value);
		}
		return defaultStates;
	}

	private static void addStateToResult(JsonObject obj, String key, String value)
	{
		switch (value.toLowerCase())
		{
			case "false" -> obj.add(key, new JsonPrimitive(false));
			case "true" -> obj.add(key, new JsonPrimitive(true));
			default -> obj.add(key, new JsonPrimitive(value));
		}
	}

	private static void extractCustomBlockDataProperties(BlockData data, JsonObject obj)
	{
		extractNumericProperties(data, obj);
		extractCollectionProperties(data, obj);
	}

	private static void extractNumericProperties(BlockData data, JsonObject obj)
	{
		if (data instanceof Ageable ageable) obj.addProperty("maxAge", String.valueOf(ageable.getMaximumAge()));
		if (data instanceof AnaloguePowerable analoguePowerable) obj.addProperty("maxPower", String.valueOf(analoguePowerable.getMaximumPower()));
		if (data instanceof Sapling sapling) obj.addProperty("maxStage", String.valueOf(sapling.getMaximumStage()));
		if (data instanceof Levelled levelled)
		{
			obj.addProperty("maxLevel", String.valueOf(levelled.getMaximumLevel()));
			obj.addProperty("minLevel", String.valueOf(levelled.getMinimumLevel()));
		}
		if (data instanceof Brushable brushable) obj.addProperty("maxDusted", String.valueOf(brushable.getMaximumDusted()));
		if (data instanceof Farmland farmland) obj.addProperty("maxMoisture", String.valueOf(farmland.getMaximumMoisture()));
		if (data instanceof Hatchable hatchable) obj.addProperty("maxHatch", String.valueOf(hatchable.getMaximumHatch()));
		if (data instanceof TurtleEgg turtleEgg)
		{
			obj.addProperty("minEggs", String.valueOf(turtleEgg.getMinimumEggs()));
			obj.addProperty("maxEggs", String.valueOf(turtleEgg.getMaximumEggs()));
		}
		if (data instanceof RespawnAnchor respawnAnchor) obj.addProperty("maxCharges", String.valueOf(respawnAnchor.getMaximumCharges()));
		if (data instanceof ChiseledBookshelf chiseledBookshelf) obj.addProperty("maxOccupiedSlots", chiseledBookshelf.getMaximumOccupiedSlots());
		if (data instanceof Leaves leaves)
		{
			obj.addProperty("maxDistance", leaves.getMaximumDistance());
			obj.addProperty("minDistance", leaves.getMinimumDistance());
		}
		if (data instanceof Beehive beehive) obj.addProperty("maxHoneyLevel", beehive.getMaximumHoneyLevel());
		if (data instanceof Cake cake) obj.addProperty("maxBites", cake.getMaximumBites());
	}

	private static void extractCollectionProperties(BlockData data, JsonObject obj)
	{
		if (data instanceof Directional directional)
		{
			JsonArray array = new JsonArray();
			directional.getFaces().stream().map(Enum::name).forEach(array::add);
			obj.add("faces", array);
		}
		if (data instanceof MultipleFacing multipleFacing)
		{
			JsonArray array = new JsonArray();
			multipleFacing.getAllowedFaces().stream().map(Enum::name).forEach(array::add);
			obj.add("faces", array);
		}
		if (data instanceof CreakingHeart creakingHeart)
		{
			JsonArray array = new JsonArray();
			creakingHeart.getAxes().stream().map(Enum::name).forEach(array::add);
			obj.add("axes", array);
		}
	}

}
