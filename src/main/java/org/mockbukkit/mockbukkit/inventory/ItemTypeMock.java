package org.mockbukkit.mockbukkit.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.papermc.paper.datacomponent.DataComponentType;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.BlockType;
import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import org.mockbukkit.mockbukkit.inventory.meta.ItemMetaMock;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings(
{"UnstableApiUsage", "unchecked"})
public class ItemTypeMock<M extends ItemMeta>
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.ItemType_TypedBaseMock<M>
{

	private final NamespacedKey namespacedKey;

	@Nullable
	private final NamespacedKey blockType;

	private final int maxStackSize;

	private final short maxDurability;

	private final boolean edible;

	private final boolean hasRecord;

	private final boolean fuel;

	private final String translationKey;

	private final Class<M> metaClass;

	private final ItemRarity rarity;

	private final CreativeCategory creativeCategory;

	private final boolean isCompostable;

	private final BigDecimal compostChance;

	private final int burnDuration;

	@NotNull
	private final Set<DataComponentType> defaultDataTypes;

	@NotNull
	private final Map<DataComponentType, Object> defaultData;

	private ItemTypeMock(NamespacedKey namespacedKey, int maxStackSize, short maxDurability, boolean edible,
			boolean hasRecord, boolean fuel, @Nullable NamespacedKey blockType, String translationKey,
			Class<M> metaClass, ItemRarity rarity, CreativeCategory creativeCategory, boolean isCompostable,
			BigDecimal compostChance, int burnDuration, Set<DataComponentType> defaultDataTypes,
			Map<DataComponentType, Object> defaultData)
	{
		this.namespacedKey = namespacedKey;
		this.maxStackSize = maxStackSize;
		this.maxDurability = maxDurability;
		this.edible = edible;
		this.hasRecord = hasRecord;
		this.fuel = fuel;
		this.blockType = blockType;
		this.translationKey = translationKey;
		this.metaClass = metaClass;
		this.rarity = rarity;
		this.creativeCategory = creativeCategory;
		this.isCompostable = isCompostable;
		this.compostChance = compostChance;
		this.burnDuration = burnDuration;
		this.defaultDataTypes = Set.copyOf(defaultDataTypes);
		this.defaultData = Map.copyOf(defaultData);
	}

	@ApiStatus.Internal
	public static ItemType from(JsonObject jsonObject)
	{
		NamespacedKey key = NamespacedKey.fromString(jsonObject.get("key").getAsString());
		int maxStackSize = jsonObject.get("maxStackSize").getAsInt();
		short maxDurability = jsonObject.get("maxDurability").getAsShort();
		boolean edible = jsonObject.get("edible").getAsBoolean();
		boolean hasRecord = jsonObject.get("record").getAsBoolean();
		boolean fuel = jsonObject.get("fuel").getAsBoolean();
		NamespacedKey blockType = jsonObject.has("blockType")
				? NamespacedKey.fromString(jsonObject.get("blockType").getAsString())
				: null;
		String translationKey = jsonObject.get("translationKey").getAsString();
		ItemRarity rarity = ItemRarity.valueOf(jsonObject.get("itemRarity").getAsString());
		CreativeCategory creativeCategory = CreativeCategory.valueOf(jsonObject.get("creativeCategory").getAsString());
		boolean isCompostable = jsonObject.get("compostable").getAsBoolean();
		int burnDuration = jsonObject.get("burnDuration").getAsInt();
		BigDecimal compostChance = BigDecimal.ZERO;
		if (isCompostable)
		{
			compostChance = BigDecimal.valueOf(jsonObject.get("compostChance").getAsFloat());
		}
		Class<? extends ItemMeta> metaClass = parseMetaClass(jsonObject);
		Set<DataComponentType> defaultDataTypes = parseDefaultDataTypes(jsonObject);
		Map<DataComponentType, Object> defaultData = parseDefaultData(jsonObject);
		return new ItemTypeMock<>(key, maxStackSize, maxDurability, edible, hasRecord, fuel, blockType, translationKey,
				metaClass, rarity, creativeCategory, isCompostable, compostChance, burnDuration, defaultDataTypes,
				defaultData);
	}

	private static Class<? extends ItemMeta> parseMetaClass(JsonObject jsonObject)
	{
		String metaClassKey = "metaClass";
		if (!jsonObject.has(metaClassKey))
		{
			return null;
		}
		String metaClassAsString = jsonObject.get(metaClassKey).getAsString();
		if (metaClassAsString.equals("BlockDataMeta") || metaClassAsString.equals("MusicInstrumentMeta"))
		{
			// Unimplemented Meta class, falling back to ItemMeta
			return ItemMetaMock.class;
		}
		try
		{
			String metaClassName = "org.mockbukkit.mockbukkit.inventory.meta." + metaClassAsString + "Mock";
			return (Class<? extends ItemMeta>) Class.forName(metaClassName);
		} catch (ClassNotFoundException e)
		{
			throw new IllegalStateException("Could not find class: " + metaClassAsString);
		}
	}

	private static Set<DataComponentType> parseDefaultDataTypes(JsonObject jsonObject)
	{
		Set<DataComponentType> defaultDataTypes = new HashSet<>();
		if (jsonObject.has("defaultDataTypes"))
		{
			JsonArray typesArray = jsonObject.getAsJsonArray("defaultDataTypes");
			for (JsonElement element : typesArray)
			{
				NamespacedKey typeKey = NamespacedKey.fromString(element.getAsString());
				DataComponentType type = Registry.DATA_COMPONENT_TYPE.get(typeKey);
				if (type != null)
				{
					defaultDataTypes.add(type);
				}
			}
		}
		return defaultDataTypes;
	}

	private static Map<DataComponentType, Object> parseDefaultData(JsonObject jsonObject)
	{
		Map<DataComponentType, Object> defaultData = new HashMap<>();
		if (jsonObject.has("defaultData"))
		{
			JsonObject dataObject = jsonObject.getAsJsonObject("defaultData");
			for (Map.Entry<String, JsonElement> entry : dataObject.entrySet())
			{
				NamespacedKey typeKey = NamespacedKey.fromString(entry.getKey());
				DataComponentType type = Registry.DATA_COMPONENT_TYPE.get(typeKey);
				if (type != null)
				{
					Object value = deserializeComponent(type, entry.getValue());
					if (value != null)
					{
						defaultData.put(type, value);
					}
				}
			}
		}
		return defaultData;
	}

	@Nullable
	private static Object deserializeComponent(DataComponentType type, JsonElement json)
	{
		if (json.isJsonPrimitive())
		{
			com.google.gson.JsonPrimitive primitive = json.getAsJsonPrimitive();
			if (primitive.isNumber())
			{
				return primitive.getAsNumber();
			}
			if (primitive.isBoolean())
			{
				return primitive.getAsBoolean();
			}
			if (primitive.isString())
			{
				String string = primitive.getAsString();
				if (string.startsWith("{") && string.endsWith("}"))
				{
					try
					{
						return GsonComponentSerializer.gson().deserialize(string);
					} catch (Exception e)
					{
						return string;
					}
				}
				return string;
			}
		}
		// Complex components should use their respective Mock deserialize method if
		// available
		// For now, we return the raw map if it's an object
		if (json.isJsonObject())
		{
			// Convert JsonObject to Map
			return new Gson().fromJson(json, Map.class);
		}
		if (json.isJsonArray())
		{
			// Convert JsonArray to List
			return new Gson().fromJson(json, List.class);
		}
		return null;
	}

	@Override
	@NotNull
	public ItemStack createItemStack()
	{
		return this.createItemStack(1);
	}

	@Override
	@NotNull
	public ItemStack createItemStack(int amount)
	{
		return new ItemStackMock(Registry.MATERIAL.get(this.getKey()), amount);
	}

	@Override
	public boolean hasBlockType()
	{
		return this.blockType != null;
	}

	@Override
	@NotNull
	public BlockType getBlockType()
	{
		if (this == AIR)
		{
			return BlockType.AIR;
		}
		Preconditions.checkArgument(this.blockType != null, "The item type %s has no corresponding block type",
				this.getKey());
		BlockType block = Registry.BLOCK.get(this.blockType);
		Preconditions.checkState(block != null && block != ItemType.AIR,
				"The item type %s has no corresponding item type", this.getKey());
		return block;
	}

	@Override
	@NotNull
	public Class<M> getItemMetaClass()
	{
		if (this == ItemType.AIR)
		{
			throw new UnsupportedOperationException("Air does not have ItemMeta");
		}
		return this.metaClass;
	}

	@Override
	public int getMaxStackSize()
	{
		return this.maxStackSize;
	}

	@Override
	public short getMaxDurability()
	{
		return this.maxDurability;
	}

	@Override
	public boolean isEdible()
	{
		return this.edible;
	}

	@Override
	public boolean isRecord()
	{
		return this.hasRecord;
	}

	@Override
	public boolean isFuel()
	{
		return this.fuel;
	}

	@Override
	public int getBurnDuration()
	{
		return burnDuration;
	}

	@Override
	public boolean isCompostable()
	{
		return this.isCompostable;
	}

	@Override
	public float getCompostChance()
	{
		Preconditions.checkArgument(this.isCompostable(), "The item type %s is not compostable", this.getKey());
		return this.compostChance.floatValue();
	}

	@Override
	@Nullable
	public ItemType getCraftingRemainingItem()
	{
		return null;
	}

	@Override
	@NotNull
	@Unmodifiable
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers()
	{
		return ImmutableMultimap.of();
	}

	@Override
	@NotNull
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot)
	{
		return ImmutableMultimap.of();
	}

	@Override
	@Nullable
	public CreativeCategory getCreativeCategory()
	{
		return this.creativeCategory;
	}

	@Override
	@Nullable
	public Material asMaterial()
	{
		return Registry.MATERIAL.get(this.namespacedKey);
	}

	@Override
	@NotNull
	public NamespacedKey getKey()
	{
		return namespacedKey;
	}

	@Override
	@NotNull
	public String getTranslationKey()
	{
		return translationKey;
	}

	@Override
	@Nullable
	public ItemRarity getItemRarity()
	{
		return this.rarity;
	}

	@Override
	@Nullable
	public <T> T getDefaultData(DataComponentType.@NotNull Valued<T> valued)
	{
		if (this.defaultData.containsKey(valued))
		{
			return (T) this.defaultData.get(valued);
		}
		// Fallbacks for common values that we already have in fields
		if (valued.getKey().equals(NamespacedKey.minecraft("max_stack_size")))
		{
			return (T) Integer.valueOf(this.maxStackSize);
		}
		if (valued.getKey().equals(NamespacedKey.minecraft("rarity")))
		{
			return (T) this.rarity;
		}
		if (valued.getKey().equals(NamespacedKey.minecraft("max_damage")) && this.maxDurability > 0)
		{
			return (T) Integer.valueOf(this.maxDurability);
		}
		return null;
	}

	@Override
	public boolean hasDefaultData(@NotNull DataComponentType dataComponentType)
	{
		return this.defaultDataTypes.contains(dataComponentType);
	}

	@Override
	@Unmodifiable
	@NotNull
	public Set<DataComponentType> getDefaultDataTypes()
	{
		return java.util.Collections.unmodifiableSet(this.defaultDataTypes);
	}

	@Override
	@NotNull
	public String translationKey()
	{
		return translationKey;
	}
}
