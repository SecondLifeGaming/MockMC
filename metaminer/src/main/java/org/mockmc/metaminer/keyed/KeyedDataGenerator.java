package org.mockmc.metaminer.keyed;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.GameRule;
import org.bukkit.Keyed;
import org.bukkit.Registry;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.generator.structure.Structure;
import org.bukkit.inventory.ItemType;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.metaminer.DataGenerator;
import org.mockmc.metaminer.json.ElementFactory;
import org.mockmc.metaminer.util.JsonUtil;

import java.io.File;
import java.io.IOException;

import java.util.logging.Logger;

public class KeyedDataGenerator implements DataGenerator
{

	private static final Logger LOGGER = Logger.getLogger(KeyedDataGenerator.class.getName());
	private static final String LEVEL_KEY = "level";

	private final File dataFolder;

	public KeyedDataGenerator(@NotNull File dataFolder)
	{
		this.dataFolder = new File(dataFolder, "keyed");
	}

	@Override
	public void generateData() throws java.io.IOException
	{
		try
		{
			RegistryAccess.registryAccess();
		}
		catch (IllegalStateException _)
		{
			LOGGER.warning("Skipping KeyedDataGenerator: RegistryAccess not found (not running in a server environment)");
			return;
		}

		if (!this.dataFolder.exists() && !this.dataFolder.mkdirs())
		{
			throw new IOException("Could not make directory: " + this.dataFolder);
		}

		for (RegistryKey<? extends Keyed> registryKey : KeyedClassTracker.getClassRegistryKeyRelation().keySet())
		{
			Registry<? extends Keyed> registry = RegistryAccess.registryAccess().getRegistry(registryKey);
			JsonArray array = new JsonArray();
			for (Keyed keyed : registry)
			{
				JsonObject keyedObjectData = MethodDataScanner.findMethodData(keyed);
				addNonTrivialData(keyedObjectData, keyed);
				array.add(keyedObjectData);
			}
			File destinationFile = new File(dataFolder, registryKey.key().value() + ".json");
			JsonObject rootObject = new JsonObject();
			rootObject.add("values", array);
			JsonUtil.dump(rootObject, destinationFile);
		}
	}

	private void addNonTrivialData(JsonObject jsonObject, Keyed keyed)
	{
		if (keyed instanceof ItemType itemType)
		{
			addItemTypeProperties(jsonObject, itemType);
		}
		if (keyed instanceof DamageType damageType)
		{
			addDamageTypeProperties(jsonObject, damageType);
		}
		if (keyed instanceof PotionEffectType potionEffectType)
		{
			addPotionEffectTypeProperties(jsonObject, potionEffectType);
		}
		if (keyed instanceof Enchantment enchantment)
		{
			addEnchantmentProperties(jsonObject, enchantment);
		}
		if (keyed instanceof Structure structure)
		{
			jsonObject.add("type", new JsonPrimitive(structure.getStructureType().getKey().toString()));
		}
		if (keyed instanceof DataComponentType dataComponentType)
		{
			jsonObject.add("valued", new JsonPrimitive(dataComponentType instanceof DataComponentType.Valued<?>));
		}
		if (keyed instanceof GameRule<?> gameRule)
		{
			jsonObject.addProperty("type", gameRule.getType().getName());
		}
	}

	private void addItemTypeProperties(JsonObject jsonObject, ItemType itemType)
	{
		if (itemType.isCompostable())
		{
			jsonObject.add("compostChance", new JsonPrimitive("%sF".formatted(itemType.getCompostChance())));
		}
		if (itemType != ItemType.AIR)
		{
			jsonObject.add("metaClass", new JsonPrimitive(itemType.getItemMetaClass().getSimpleName()));
			JsonObject defaultData = new JsonObject();
			for (DataComponentType type : itemType.getDefaultDataTypes())
			{
				if (type instanceof DataComponentType.Valued<?> valued)
				{
					Object value = itemType.getDefaultData(valued);
					com.google.gson.JsonElement element = ElementFactory.toJson(value);
					if (element != null)
					{
						defaultData.add(type.getKey().toString(), element);
					}
				}
			}
			if (!defaultData.isEmpty())
			{
				jsonObject.add("defaultData", defaultData);
			}
		}
	}

	private void addDamageTypeProperties(JsonObject jsonObject, DamageType damageType)
	{
		jsonObject.add("damageScaling", new JsonPrimitive(damageType.getDamageScaling().toString()));
		org.bukkit.NamespacedKey soundKey = Registry.SOUNDS.getKey(damageType.getDamageEffect().getSound());
		if (soundKey != null)
		{
			jsonObject.add("sound", new JsonPrimitive(soundKey.toString()));
		}
		jsonObject.add("deathMessageType", new JsonPrimitive(damageType.getDeathMessageType().toString()));
	}

	private void addPotionEffectTypeProperties(JsonObject jsonObject, PotionEffectType potionEffectType)
	{
		jsonObject.add("rgb", new JsonPrimitive(potionEffectType.getColor().asRGB()));
		jsonObject.add("category", new JsonPrimitive(potionEffectType.getEffectCategory().toString()));
	}

	/**
	 * Add the given enchantment's properties to the given jsonObject
	 *
	 * @param jsonObject  the jsonObject to add the enchantment's properties to
	 * @param enchantment the enchantment to add the properties of
	 */
	private void addEnchantmentProperties(JsonObject jsonObject, Enchantment enchantment)
	{
		JsonArray displayNames = new JsonArray();
		JsonArray minModifiedCosts = new JsonArray();
		JsonArray maxModifiedCosts = new JsonArray();
		for (int i = 1; i <= enchantment.getMaxLevel(); i++)
		{
			GsonComponentSerializer serializer = GsonComponentSerializer.builder().build();
			JsonObject displayName = new JsonObject();
			displayName.add(LEVEL_KEY, new JsonPrimitive(i));
			displayName.add("text", serializer.serializeToTree(enchantment.displayName(i)));
			displayNames.add(displayName);

			JsonObject minModifiedCost = new JsonObject();
			minModifiedCost.add(LEVEL_KEY, new JsonPrimitive(i));
			minModifiedCost.add("cost", new JsonPrimitive(enchantment.getMinModifiedCost(i)));
			minModifiedCosts.add(minModifiedCost);

			JsonObject maxModifiedCost = new JsonObject();
			maxModifiedCost.add(LEVEL_KEY, new JsonPrimitive(i));
			maxModifiedCost.add("cost", new JsonPrimitive(enchantment.getMaxModifiedCost(i)));
			maxModifiedCosts.add(maxModifiedCost);
		}
		jsonObject.add("displayNames", displayNames);
		jsonObject.add("minModifiedCosts", minModifiedCosts);
		jsonObject.add("maxModifiedCosts", maxModifiedCosts);

		JsonArray conflicts = new JsonArray();
		for (Enchantment otherEnchantment : RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT))
		{
			if (enchantment.conflictsWith(otherEnchantment))
			{
				conflicts.add(otherEnchantment.getKey().toString());
			}
		}
		jsonObject.add("conflicts", conflicts);
		jsonObject.add("exclusiveWith", ElementFactory.toJson(enchantment.getExclusiveWith()));
		jsonObject.add("supportedItems", ElementFactory.toJson(enchantment.getSupportedItems()));
		jsonObject.add("primaryItems", ElementFactory.toJson(enchantment.getPrimaryItems()));
		jsonObject.add("weight", new JsonPrimitive(enchantment.getWeight()));
		jsonObject.add("description", ElementFactory.toJson(enchantment.description()));
	}

}
