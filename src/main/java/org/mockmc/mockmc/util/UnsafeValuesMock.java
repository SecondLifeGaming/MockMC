package org.mockmc.mockmc.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.flattener.ComponentFlattener;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.translation.Translatable;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Tag;
import org.bukkit.UnsafeValues;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.damage.DamageSourceBuilderMock;
import org.mockmc.mockmc.exception.ItemSerializationException;
import org.mockmc.mockmc.inventory.ItemStackMock;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.inventory.meta.ItemMetaMock;
import org.mockmc.mockmc.inventory.serializer.SerializationUtils;
import org.mockmc.mockmc.plugin.lifecycle.event.LifecycleEventManagerMock;
import org.mockmc.mockmc.potion.InternalPotionDataMock;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock implementation of an {@link UnsafeValues}.
 *
 * @deprecated Use modern API instead.
 */
@SuppressWarnings(
{"unchecked", "removal", "java:S1133"})
@Deprecated(since = "1.7.2")
public class UnsafeValuesMock implements org.mockmc.mockmc.generated.server.org.bukkit.UnsafeValuesBaseMock
{

	private static final Logger LOGGER = Logger.getLogger(UnsafeValuesMock.class.getName());

	private static final List<String> COMPATIBLE_API_VERSIONS = List.of("1.13", "1.14", "1.15", "1.16", "1.17", "1.18",
			"1.19", "1.20", "1.21", "26.1");

	private static final String PROPERTY_SCHEMA_VERSION = "schema_version";

	private static final String FIELD_COUNT = "count";

	private static final String FIELD_AMOUNT = "amount";

	private static final Map<String, String> RENAME_JSON_PROPERTY = ImmutableMap.<String, String>builder()
			.putAll(toMinecraft(ItemMetaMock.DAMAGE)).putAll(toMinecraft(ItemMetaMock.MAX_DAMAGE))
			.putAll(toMinecraft(ItemMetaMock.REPAIR_COST)).putAll(toMinecraft(ItemMetaMock.ENCHANTMENTS))
			.putAll(toMinecraft(ItemMetaMock.LORE_KEY)).putAll(toMinecraft(ItemMetaMock.UNBREAKABLE))
			.putAll(toMinecraft(ItemMetaMock.CUSTOM_MODEL_DATA)).putAll(toMinecraft(ItemMetaMock.HIDE_TOOLTIP))
			.putAll(toMinecraft(ItemMetaMock.FIRE_RESISTANT)).putAll(toMinecraft(ItemMetaMock.RARITY))
			.putAll(toMinecraft(ItemMetaMock.ITEM_NAME)).putAll(toMinecraft(ItemMetaMock.ITEM_MODEL))
			.putAll(toMinecraft(ItemMetaMock.TOOL_TIP_STYLE))
			.putAll(toMinecraft(ItemMetaMock.ENCHANTMENT_GLINT_OVERRIDE)).putAll(toMinecraft(ItemMetaMock.GLIDER))
			.putAll(toMinecraft(ItemMetaMock.MAX_STACK_SIZE)).putAll(toMinecraft(ItemMetaMock.DAMAGE_RESISTANT))
			.putAll(toMinecraft(ItemMetaMock.ENCHANTABLE)).putAll(toMinecraft(ItemMetaMock.USE_REMAINDER))
			.putAll(toMinecraft(ItemMetaMock.USE_COOLDOWN)).putAll(toMinecraft(ItemMetaMock.FOOD))
			.putAll(toMinecraft(ItemMetaMock.TOOL)).putAll(toMinecraft(ItemMetaMock.EQUIPPABLE))
			.putAll(toMinecraft(ItemMetaMock.JUKEBOX_PLAYABLE)).put("invisible", "minecraft:invisible")
			.put("marker", "minecraft:marker").put("no-base-plate", "minecraft:no_base_plate")
			.put("show-arms", "minecraft:show_arms").put("small", "minecraft:small")
			.put(ItemMetaMock.DISPLAY_NAME, "minecraft:custom_name")
			.put(ItemMetaMock.PUBLIC_BUKKIT_VALUES, "minecraft:custom_data").build();

	private static Map<String, String> toMinecraft(final String key)
	{
		String newName = key.toLowerCase(Locale.ROOT);
		newName = newName.replace("-", "_");
		return Map.of(key, NamespacedKey.minecraft(newName).asString());
	}

	private String minimumApiVersion = "none";

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public ComponentFlattener componentFlattener()
	{
		return ComponentFlattener.basic();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.18")
	@NotNull
	public PlainComponentSerializer plainComponentSerializer()
	{
		return PlainComponentSerializer.plain();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.18")
	@NotNull
	public PlainTextComponentSerializer plainTextSerializer()
	{
		return PlainTextComponentSerializer.plainText();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.18")
	@NotNull
	public GsonComponentSerializer gsonComponentSerializer()
	{
		return GsonComponentSerializer.gson();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.18")
	@NotNull
	public GsonComponentSerializer colorDownsamplingGsonComponentSerializer()
	{
		return GsonComponentSerializer.colorDownsamplingGson();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.18")
	@NotNull
	public LegacyComponentSerializer legacyComponentSerializer()
	{
		return LegacyComponentSerializer.legacySection();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public Material fromLegacy(MaterialData material)
	{
		return fromLegacy(material, false);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public int getDataVersion()
	{
		return 1;
	}

	/**
	 * Sets the minimum api-version allowed.
	 *
	 * @param minimumApiVersion
	 *            The minimum API version to support.
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.13")
	public void setMinimumApiVersion(String minimumApiVersion)
	{
		this.minimumApiVersion = minimumApiVersion;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public void checkSupported(@NotNull PluginDescriptionFile pdf) throws InvalidPluginException
	{
		if (pdf.getAPIVersion() == null)
		{
			if (COMPATIBLE_API_VERSIONS.contains(minimumApiVersion))
			{
				throw new InvalidPluginException("Plugin does not specify an 'api-version' in its plugin.yml.");
			}
		} else
		{
			int pluginIndex = COMPATIBLE_API_VERSIONS.indexOf(pdf.getAPIVersion());
			if (pluginIndex == -1)
			{
				throw new InvalidPluginException("Unsupported API version " + pdf.getAPIVersion());
			}
			if (pluginIndex < COMPATIBLE_API_VERSIONS.indexOf(minimumApiVersion))
			{
				throw new InvalidPluginException(
						"Plugin API version " + pdf.getAPIVersion() + " is lower than the minimum allowed version.");
			}
		}
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public byte[] processClass(PluginDescriptionFile pdf, String path, byte[] clazz)
	{
		return clazz;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.19")
	public boolean isSupportedApiVersion(String apiVersion)
	{
		return COMPATIBLE_API_VERSIONS.contains(apiVersion);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public byte[] serializeItem(ItemStack itemstack)
	{
		Preconditions.checkNotNull(itemstack, "null cannot be serialized");
		LOGGER.log(Level.FINE, "serializeStack: {0}", itemstack.getType());
		Map<String, Object> stack = this.serializeStack(itemstack);
		Object prepared = recursivePrepareForSerialization(stack);
		try (java.io.ByteArrayOutputStream bao = new java.io.ByteArrayOutputStream();
				java.util.zip.GZIPOutputStream gzos = new java.util.zip.GZIPOutputStream(bao))
		{
			ObjectOutputStream oos = new ObjectOutputStream(gzos);
			oos.writeObject(prepared);
			oos.flush();
			gzos.finish();
			return bao.toByteArray();
		} catch (IOException e)
		{
			throw new ItemSerializationException(e);
		}
	}

	private Object recursivePrepareForSerialization(Object obj)
	{
		if (obj instanceof NamespacedKey key)
		{
			return key.asString();
		}
		if (obj instanceof org.bukkit.configuration.serialization.ConfigurationSerializable serializable)
		{
			Map<String, Object> map = new HashMap<>(serializable.serialize());
			map.computeIfAbsent(org.bukkit.configuration.serialization.ConfigurationSerialization.SERIALIZED_TYPE_KEY,
					k -> org.bukkit.configuration.serialization.ConfigurationSerialization
							.getAlias(serializable.getClass()));
			return recursivePrepareForSerialization(map);
		}
		if (obj instanceof Map<?, ?> map)
		{
			Map<Object, Object> newMap = new HashMap<>();
			for (Map.Entry<?, ?> entry : map.entrySet())
			{
				newMap.put(recursivePrepareForSerialization(entry.getKey()),
						recursivePrepareForSerialization(entry.getValue()));
			}
			return newMap;
		}
		if (obj instanceof java.util.Collection<?> col)
		{
			List<Object> newList = new ArrayList<>();
			for (Object val : col)
			{
				newList.add(recursivePrepareForSerialization(val));
			}
			return newList;
		}
		if (obj instanceof com.google.common.collect.Multimap<?, ?> multimap)
		{
			com.google.common.collect.Multimap<Object, Object> newMultimap = com.google.common.collect.LinkedHashMultimap
					.create();
			for (Map.Entry<?, ?> entry : multimap.entries())
			{
				newMultimap.put(recursivePrepareForSerialization(entry.getKey()),
						recursivePrepareForSerialization(entry.getValue()));
			}
			return newMultimap;
		}
		return obj;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public ItemStack deserializeItem(byte[] data)
	{
		Preconditions.checkNotNull(data, "null cannot be deserialized");
		Preconditions.checkArgument(data.length > 0, "cannot deserialize nothing");
		LOGGER.log(Level.FINE, "UnsafeValuesMock.deserializeItem: data length={0}", data.length);
		try (java.io.ByteArrayInputStream bai = new java.io.ByteArrayInputStream(data);
				java.util.zip.GZIPInputStream gzis = new java.util.zip.GZIPInputStream(bai))
		{
			final ObjectInputStream ois = new ObjectInputStream(gzis)
			{
				{
					enableResolveObject(true);
				}

				@Override
				protected Object resolveObject(Object obj) throws IOException
				{
					if (obj instanceof Map<?, ?> map && map.containsKey(
							org.bukkit.configuration.serialization.ConfigurationSerialization.SERIALIZED_TYPE_KEY))
					{
						try
						{
							return org.bukkit.configuration.serialization.ConfigurationSerialization
									.deserializeObject((Map<String, Object>) map);
						} catch (Exception _)
						{
							// If it fails, return the map as is.
							return map;
						}
					}
					return super.resolveObject(obj);
				}
			};
			Object read = ois.readObject();
			if (!(read instanceof Map))
			{
				return ItemStackMock.empty();
			}
			Map<String, Object> stack = (Map<String, Object>) read;
			return this.deserializeStack(stack);
		} catch (Exception e)
		{
			throw new ItemSerializationException(e);
		}
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public JsonObject serializeItemAsJson(@NotNull ItemStack item)
	{
		return SerializationUtils.createDefaultBuilder().toJsonTree(serializeStack(item)).getAsJsonObject();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public ItemStack deserializeItemFromJson(@NotNull JsonObject jsonObject) throws IllegalArgumentException
	{
		Map<String, Object> args = SerializationUtils.createDefaultBuilder().fromJson(jsonObject, Map.class);
		return deserializeStack(args);
	}

	/**
	 * @deprecated Use modern API instead.
	 */

	/**
	 * @deprecated Use modern API instead.
	 */

	/**
	 * @deprecated Use modern API instead.
	 */

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.21", forRemoval = true)
	@Nullable
	public String getBlockTranslationKey(@NotNull Material material)
	{
		if (!material.isBlock())
		{
			return null;
		}
		// edge cases: WHEAT and NETHER_WART are blocks, but still use the "item" prefix
		if (material == Material.WHEAT || material == Material.NETHER_WART)
		{
			return formatTranslatable("item", material);
		}
		return formatTranslatable("block", material);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.21", forRemoval = true)
	@Nullable
	public String getItemTranslationKey(@NotNull Material material)
	{
		if (!material.isItem())
		{
			return null;
		}
		String edgeCaseHandledTranslationKey = handleTranslateItemEdgeCases(material);
		if (edgeCaseHandledTranslationKey != null)
		{
			return edgeCaseHandledTranslationKey;
		}
		return formatTranslatable("item", material);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@Nullable
	public String getTranslationKey(@NotNull EntityType type)
	{
		Preconditions.checkArgument(type.getName() != null, "Invalid name of EntityType %s for translation key", type);
		return formatTranslatable("entity", type);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@Nullable
	public String getTranslationKey(@NotNull ItemStack itemStack)
	{
		if (itemStack.getType().isItem())
		{
			Material material = itemStack.getType();
			String edgeCaseHandledTranslationKey = handleTranslateItemEdgeCases(material);
			return Objects.requireNonNullElseGet(edgeCaseHandledTranslationKey,
					() -> formatTranslatable("item", material, true));
		} else if (itemStack.getType().isBlock())
		{
			return getBlockTranslationKey(itemStack.getType());
		} else
		{
			return null;
		}
	}

	private String handleTranslateItemEdgeCases(Material material)
	{
		// edge cases: WHEAT and NETHER_WART are blocks, but still use the "item" prefix
		// (therefore this check has to be done BEFORE the isBlock check below)
		if (material == Material.WHEAT || material == Material.NETHER_WART)
		{
			return formatTranslatable("item", material);
		}
		// edge case: If a translation key from an item is requested from anything that
		// is also a block, the block translation key is always returned
		// e.g: Material#STONE is a block (but also an obtainable item in the
		// inventory). However, the translation key is always "block.minecraft.stone".
		if (material.isBlock())
		{
			return formatTranslatable("block", material);
		}
		// not an edge case
		return null;
	}

	private <T extends Keyed & Translatable> String formatTranslatable(String prefix, T translatable,
			boolean fromItemStack)
	{
		// enforcing Translatable is not necessary, but translating only makes sense
		// when the object is really translatable by design.
		String value = translatable.key().value();
		if (translatable instanceof Material material)
		{
			// replace wall_hanging string check with Tag check (when implemented)
			if (value.contains("wall_hanging") || Tag.WALL_SIGNS.isTagged(material) || value.endsWith("wall_banner")
					|| value.endsWith("wall_torch") || value.endsWith("wall_skull") || value.endsWith("wall_skull")
					|| value.endsWith("wall_head"))
			{
				value = value.replace("wall_", "");
			}
			final Set<Material> emptyEffects = Set.of(Material.POTION, Material.SPLASH_POTION, Material.TIPPED_ARROW,
					Material.LINGERING_POTION);
			if (fromItemStack && emptyEffects.contains(material))
			{
				value += ".effect.empty";
			}
		}
		return String.format("%s.%s.%s", prefix, translatable.key().namespace(), value);
	}

	private <T extends Keyed & Translatable> String formatTranslatable(String prefix, T translatable)
	{
		return formatTranslatable(prefix, translatable, false);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public PotionType.InternalPotionData getInternalPotionData(NamespacedKey key)
	{
		return new InternalPotionDataMock(key);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public DamageSource.@NotNull Builder createDamageSourceBuilder(@NotNull DamageType damageType)
	{
		return new DamageSourceBuilderMock(damageType);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public <B extends org.bukkit.Keyed> B get(RegistryKey<B> registryKey, NamespacedKey namespacedKey)
	{
		return RegistryAccess.registryAccess().getRegistry(registryKey).get(namespacedKey);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public String getMainLevelName()
	{
		// Currently hardcoded to "world". Will be configurable when server properties
		// are implemented.
		return "world";
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public LifecycleEventManager<Plugin> createPluginLifecycleEventManager(JavaPlugin javaPlugin,
			BooleanSupplier booleanSupplier)
	{
		return new LifecycleEventManagerMock<>(javaPlugin, booleanSupplier);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public ItemStack createEmptyStack()
	{
		return ItemStackMock.empty();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public Map<String, Object> serializeStack(ItemStack itemStack)
	{
		if (itemStack.isEmpty())
		{
			return Map.of("id", "minecraft:air", "DataVersion", this.getDataVersion(), PROPERTY_SCHEMA_VERSION, 1);
		}
		LOGGER.log(Level.FINE, "serializeStack: {0}", itemStack.getType());
		Map<String, Object> result = new HashMap<>();
		result.put("id", itemStack.getType().getKey().asString());
		result.put(FIELD_COUNT, itemStack.getAmount());
		result.put("DataVersion", this.getDataVersion());
		result.put(PROPERTY_SCHEMA_VERSION, 1);
		Map<String, Object> serializedMeta = itemStack.getItemMeta().serialize();
		if (!serializedMeta.isEmpty())
		{
			for (Map.Entry<String, String> entry : RENAME_JSON_PROPERTY.entrySet())
			{
				String originalName = entry.getKey();
				String newName = entry.getValue();
				if (serializedMeta.containsKey(originalName))
				{
					var value = serializedMeta.remove(originalName);
					serializedMeta.put(newName, value);
				}
			}
			result.put("components", serializedMeta);
		}
		return result;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public ItemStack deserializeStack(@NotNull Map<String, Object> args)
	{
		LOGGER.log(Level.FINE, "deserializeStack: args={0}", args);
		try
		{
			final String id = (String) args.getOrDefault("id", args.get("type"));
			final int amount = parseAmount(args);
			Map<String, Object> components = (Map<String, Object>) args.getOrDefault("components", args.get("meta"));
			if (components != null)
			{
				renameLegacyComponents(components);
			}
			Material material = resolveMaterial(id, args);
			LOGGER.log(Level.FINE, "resolveMaterial returned: {0}", material);
			if (material == null || material.isAir())
			{
				return ItemStackMock.empty();
			}
			@NotNull
			ItemStack itemstack = ItemStack.of(material, amount);
			if (components != null)
			{
				applyMeta(itemstack, components);
			}
			return itemstack;
		} catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, e,
					() -> "deserializeStack FAILED: " + e.getClass().getName() + ": " + e.getMessage());
			return ItemStackMock.empty();
		}
	}

	private int parseAmount(Map<String, Object> args)
	{
		if (args.containsKey(FIELD_COUNT))
		{
			return ((Number) args.get(FIELD_COUNT)).intValue();
		}
		if (args.containsKey(FIELD_AMOUNT))
		{
			return ((Number) args.get(FIELD_AMOUNT)).intValue();
		}
		return 1;
	}

	private void renameLegacyComponents(Map<String, Object> components)
	{
		for (Map.Entry<String, String> entry : RENAME_JSON_PROPERTY.entrySet())
		{
			String originalName = entry.getValue();
			String newName = entry.getKey();
			if (components.containsKey(originalName))
			{
				var value = components.get(originalName);
				components.put(newName, value);
				components.remove(originalName);
			}
		}
	}

	private Material resolveMaterial(String id, Map<String, Object> args)
	{
		NamespacedKey key = id != null ? NamespacedKey.fromString(id) : null;
		Material material = null;
		if (key != null)
		{
			material = Registry.MATERIAL.get(key);
			if (material == null)
			{
				material = Material.getMaterial(key.getKey().toUpperCase(Locale.ROOT));
			}
		}
		if (material == null && args.get("type") != null)
		{
			material = Material.getMaterial((String) args.get("type"));
		}
		return material;
	}

	private void applyMeta(ItemStack itemstack, Map<String, Object> components)
	{
		try
		{
			@Nullable
			ItemMeta meta = SerializableMeta.deserialize(components);
			Preconditions.checkArgument(meta != null, "Invalid item meta type");
			itemstack.setItemMeta(meta);
		} catch (Exception e)
		{
			throw new IllegalArgumentException("Error while deserializing item meta", e);
		}
	}

	/**
	 * @deprecated Use modern API instead.
	 */

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public Material getMaterial(String material, int version)
	{
		return Material.getMaterial(material);
	}
}
