package org.mockmc.mockmc.registry;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.Tag;
import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.GameRuleMock;
import org.mockmc.mockmc.art.ArtMock;
import org.mockmc.mockmc.attribute.AttributeMock;
import org.mockmc.mockmc.block.BiomeMock;
import org.mockmc.mockmc.block.BlockTypeMock;
import org.mockmc.mockmc.block.banner.PatternTypeMock;
import org.mockmc.mockmc.damage.DamageTypeMock;
import org.mockmc.mockmc.datacomponent.DataComponentTypeMock;
import org.mockmc.mockmc.dialog.DialogMock;
import org.mockmc.mockmc.enchantments.EnchantmentMock;
import org.mockmc.mockmc.entity.ZombieNautilusMock;
import org.mockmc.mockmc.entity.memory.MemoryModuleMock;
import org.mockmc.mockmc.entity.variant.CatSoundVariantMock;
import org.mockmc.mockmc.entity.variant.CatVariantMock;
import org.mockmc.mockmc.entity.variant.ChickenSoundVariantMock;
import org.mockmc.mockmc.entity.variant.ChickenVariantMock;
import org.mockmc.mockmc.entity.variant.CowSoundVariantMock;
import org.mockmc.mockmc.entity.variant.CowVariantMock;
import org.mockmc.mockmc.entity.variant.FrogVariantMock;
import org.mockmc.mockmc.entity.variant.PigSoundVariantMock;
import org.mockmc.mockmc.entity.variant.PigVariantMock;
import org.mockmc.mockmc.entity.variant.VillagerProfessionMock;
import org.mockmc.mockmc.entity.variant.VillagerTypeMock;
import org.mockmc.mockmc.entity.variant.WolfSoundVariantMock;
import org.mockmc.mockmc.entity.variant.WolfVariantMock;
import org.mockmc.mockmc.event.GameEventMock;
import org.mockmc.mockmc.exception.IncompatiblePaperVersionException;
import org.mockmc.mockmc.exception.UnimplementedOperationException;
import org.mockmc.mockmc.fluid.FluidMock;
import org.mockmc.mockmc.generator.structure.StructureMock;
import org.mockmc.mockmc.generator.structure.StructureTypeMock;
import org.mockmc.mockmc.inventory.ItemTypeMock;
import org.mockmc.mockmc.inventory.MenuTypeMock;
import org.mockmc.mockmc.inventory.meta.trim.TrimMaterialMock;
import org.mockmc.mockmc.inventory.meta.trim.TrimPatternMock;
import org.mockmc.mockmc.map.MapCursorTypeMock;
import org.mockmc.mockmc.potion.PotionEffectTypeMock;
import org.bukkit.potion.PotionType;
import org.mockmc.mockmc.sound.JukeboxSongMock;
import org.mockmc.mockmc.sound.MusicInstrumentMock;
import org.mockmc.mockmc.sound.SoundMock;
import org.mockmc.mockmc.util.ResourceLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Stream;

public class RegistryMock<T extends Keyed> implements org.mockmc.mockmc.generated.org.bukkit.RegistryBaseMock<T>
{
	private static final String KEY_CANNOT_BE_NULL = "key cannot be null";

	/**
	 * These classes have registries that are an exception to the others, as they
	 * are wrappers to minecraft internals
	 */
	private final Map<NamespacedKey, T> keyedMap = new HashMap<>();

	private final Map<TagKey<T>, Tag<T>> tagCache = new HashMap<>();

	private final RegistryKey<T> registryKey;

	private JsonArray keyedData;

	private Function<JsonObject, T> constructor;

	public RegistryMock(RegistryKey<T> key)
	{
		this.registryKey = key;
		loadKeyedToRegistry(key);
	}

	private void loadKeyedToRegistry(@NotNull RegistryKey<T> key)
	{
		String fileName = String.format("/keyed/%s.json", key.key().value());
		@SuppressWarnings("unchecked")
		Function<JsonObject, T> constructorFunction = (Function<JsonObject, T>) getConstructorFunction(key);
		this.constructor = constructorFunction;
		keyedData = ResourceLoader.loadResource(fileName).getAsJsonObject().get("values").getAsJsonArray();
	}

	private Function<JsonObject, ? extends Keyed> getConstructorFunction(RegistryKey<T> key)
	{
		Map<RegistryKey<?>, Function<JsonObject, ? extends Keyed>> factoryMap = new HashMap<>();
		factoryMap.put(RegistryKey.CHICKEN_SOUND_VARIANT, ChickenSoundVariantMock::from);
		factoryMap.put(RegistryKey.DIALOG, DialogMock::from);
		factoryMap.put(RegistryKey.STRUCTURE, StructureMock::from);
		factoryMap.put(RegistryKey.STRUCTURE_TYPE, StructureTypeMock::from);
		factoryMap.put(RegistryKey.TRIM_MATERIAL, TrimMaterialMock::from);
		factoryMap.put(RegistryKey.TRIM_PATTERN, TrimPatternMock::from);
		factoryMap.put(RegistryKey.INSTRUMENT, MusicInstrumentMock::from);
		factoryMap.put(RegistryKey.GAME_EVENT, GameEventMock::from);
		factoryMap.put(RegistryKey.ENCHANTMENT, EnchantmentMock::from);
		factoryMap.put(RegistryKey.MOB_EFFECT, PotionEffectTypeMock::from);
		factoryMap.put(RegistryKey.DAMAGE_TYPE, DamageTypeMock::from);
		factoryMap.put(RegistryKey.ITEM, ItemTypeMock::from);
		factoryMap.put(RegistryKey.BLOCK, BlockTypeMock::from);
		factoryMap.put(RegistryKey.WOLF_VARIANT, WolfVariantMock::from);
		factoryMap.put(RegistryKey.JUKEBOX_SONG, JukeboxSongMock::from);
		factoryMap.put(RegistryKey.CAT_VARIANT, CatVariantMock::from);
		factoryMap.put(RegistryKey.CAT_SOUND_VARIANT, CatSoundVariantMock::from);
		factoryMap.put(RegistryKey.VILLAGER_PROFESSION, VillagerProfessionMock::from);
		factoryMap.put(RegistryKey.VILLAGER_TYPE, VillagerTypeMock::from);
		factoryMap.put(RegistryKey.FROG_VARIANT, FrogVariantMock::from);
		factoryMap.put(RegistryKey.CHICKEN_VARIANT, ChickenVariantMock::from);
		factoryMap.put(RegistryKey.COW_VARIANT, CowVariantMock::from);
		factoryMap.put(RegistryKey.COW_SOUND_VARIANT, CowSoundVariantMock::from);
		factoryMap.put(RegistryKey.PIG_VARIANT, PigVariantMock::from);
		factoryMap.put(RegistryKey.WOLF_SOUND_VARIANT, WolfSoundVariantMock::from);
		factoryMap.put(RegistryKey.MAP_DECORATION_TYPE, MapCursorTypeMock::from);
		factoryMap.put(RegistryKey.MENU, MenuTypeMock::from);
		factoryMap.put(RegistryKey.BANNER_PATTERN, PatternTypeMock::from);
		factoryMap.put(RegistryKey.PAINTING_VARIANT, ArtMock::from);
		factoryMap.put(RegistryKey.ATTRIBUTE, AttributeMock::from);
		factoryMap.put(RegistryKey.BIOME, BiomeMock::from);
		factoryMap.put(RegistryKey.SOUND_EVENT, SoundMock::from);
		factoryMap.put(RegistryKey.FLUID, FluidMock::from);
		factoryMap.put(RegistryKey.DATA_COMPONENT_TYPE, DataComponentTypeMock::from);
		factoryMap.put(RegistryKey.MEMORY_MODULE_TYPE, MemoryModuleMock::from);
		factoryMap.put(RegistryKey.GAME_RULE, GameRuleMock::from);
		factoryMap.put(RegistryKey.PIG_SOUND_VARIANT, PigSoundVariantMock::from);
		factoryMap.put(RegistryKey.ZOMBIE_NAUTILUS_VARIANT, ZombieNautilusMock.VariantMock::from);
		// Remove the EntityTypeMock mapping as it's an enum
		factoryMap.remove(RegistryKey.ENTITY_TYPE);
		// Add special handling for enum-based registry types
		if (isEnumBasedRegistry(key))
		{
			return jsonObject -> createEnumWrapper(jsonObject, key);
		}
		Function<JsonObject, ? extends Keyed> constructorFunction = factoryMap.get(key);
		if (constructorFunction == null)
		{
			// TODO: Auto-generated method stub
			throw new UnimplementedOperationException();
		}
		return constructorFunction;
	}

	private boolean isEnumBasedRegistry(RegistryKey<?> key)
	{
		return key == RegistryKey.ENTITY_TYPE || key == RegistryKey.PARTICLE_TYPE || key == RegistryKey.POTION;
	}

	@SuppressWarnings(
	{"unchecked", "rawtypes"})
	private T createEnumWrapper(JsonObject jsonObject, RegistryKey<T> key)
	{
		// Extract the key from the JSON object
		String id = jsonObject.get("key").getAsString();
		NamespacedKey namespacedKey = NamespacedKey.fromString(id);
		// Get the enum class from the registry key's type parameter
		Class<?> enumClass = getEnumClassForRegistryKey(key);
		if (enumClass == null || !enumClass.isEnum())
		{
			throw new IllegalStateException("Registry key " + key + " is marked as enum but has no enum class");
		}
		// Find the enum constant by name
		String enumName = namespacedKey.getKey().toUpperCase();
		try
		{
			// Convert the enum name to the corresponding enum constant
			Enum<?> enumValue = Enum.valueOf((Class) enumClass, enumName);
			return (T) enumValue;
		} catch (IllegalArgumentException | ClassCastException e)
		{
			throw new IllegalStateException("Could not find enum constant " + enumName + " in " + enumClass, e);
		}
	}

	private Class<?> getEnumClassForRegistryKey(RegistryKey<?> key)
	{
		Map<RegistryKey<?>, Class<?>> enumMap = new HashMap<>();
		enumMap.put(RegistryKey.ENTITY_TYPE, EntityType.class);
		enumMap.put(RegistryKey.PARTICLE_TYPE, Particle.class);
		enumMap.put(RegistryKey.POTION, PotionType.class);
		return enumMap.get(key);
	}

	@Override
	@Nullable
	public T get(@NotNull NamespacedKey key)
	{
		Preconditions.checkNotNull(key);
		loadIfEmpty();
		return keyedMap.get(key);
	}

	@Override
	@Nullable
	public T get(@NotNull net.kyori.adventure.key.Key key)
	{
		Preconditions.checkArgument(key != null, KEY_CANNOT_BE_NULL);
		return get(new NamespacedKey(key.namespace(), key.value()));
	}

	@Override
	@Nullable
	public T get(@NotNull TypedKey<T> key)
	{
		Preconditions.checkArgument(key != null, KEY_CANNOT_BE_NULL);
		return get(key.key());
	}

	@Override
	@NotNull
	public T getOrThrow(@NotNull NamespacedKey namespacedKey)
	{
		Preconditions.checkNotNull(namespacedKey);
		loadIfEmpty();
		T value = this.keyedMap.get(namespacedKey);
		if (value == null)
		{
			throw new NoSuchElementException("No value for " + namespacedKey + " in " + this);
		}
		return value;
	}

	@Override
	@NotNull
	public T getOrThrow(@NotNull net.kyori.adventure.key.Key key)
	{
		Preconditions.checkArgument(key != null, KEY_CANNOT_BE_NULL);
		return getOrThrow(new NamespacedKey(key.namespace(), key.value()));
	}

	@Override
	@NotNull
	public T getOrThrow(@NotNull TypedKey<T> key)
	{
		Preconditions.checkNotNull(key, KEY_CANNOT_BE_NULL);
		return getOrThrow(key.key());
	}

	@Override
	@Nullable
	public NamespacedKey getKey(@NotNull T value)
	{
		return value.getKey();
	}

	@Override
	@NotNull
	public NamespacedKey getKeyOrThrow(@NotNull T value)
	{
		return value.getKey();
	}

	@Override
	public boolean hasTag(@NotNull TagKey<T> tagKey)
	{
		return getTag(tagKey) != null;
	}

	@Override
	public Tag<T> getTag(TagKey<T> tagKey)
	{
		Preconditions.checkNotNull(tagKey, "tagKey cannot be null");
		if (tagCache.containsKey(tagKey))
		{
			return tagCache.get(tagKey);
		}
		Tag<T> tag = loadTag(tagKey);
		if (tag != null)
		{
			tagCache.put(tagKey, tag);
		}
		return tag;
	}

	@Override
	public Collection<Tag<T>> getTags()
	{
		String plural = getPlural(registryKey);
		if (plural == null)
		{
			return Collections.emptyList();
		}
		// Use the resource loader or filesystem to find all tags
		// Since we don't have a list of all tags, we might need to scan the resources.
		// For now, return the cached tags as a fallback, but ideally we should load
		// them all.
		return tagCache.values();
	}

	@Nullable
	private Tag<T> loadTag(TagKey<T> tagKey)
	{
		String plural = getPlural(registryKey);
		if (plural == null)
		{
			return null;
		}
		String fileName = String.format("/tags/%s/%s.json", plural, tagKey.key().value());
		try
		{
			JsonObject json = ResourceLoader.loadResource(fileName).getAsJsonObject();
			JsonArray values = json.getAsJsonArray("values");
			List<T> tagValues = new ArrayList<>();
			for (JsonElement element : values)
			{
				NamespacedKey key = NamespacedKey.fromString(element.getAsString());
				T value = get(key);
				if (value != null)
				{
					tagValues.add(value);
				}
			}
			return new TagMock<>(tagKey, tagValues);
		} catch (Exception e)
		{
			return null;
		}
	}

	@Override
	@NotNull
	public Stream<T> stream()
	{
		loadIfEmpty();
		return keyedMap.values().stream();
	}

	@Override
	@NotNull
	public Stream<NamespacedKey> keyStream()
	{
		return keyedMap.keySet().stream();
	}

	@NotNull
	@Override
	public Iterator<T> iterator()
	{
		loadIfEmpty();
		return keyedMap.values().iterator();
	}

	@Override
	public int size()
	{
		loadIfEmpty();
		return keyedData.size();
	}

	private void loadIfEmpty()
	{
		if (keyedMap.isEmpty())
		{
			try
			{
				for (JsonElement structureJSONElement : keyedData)
				{
					JsonObject structureJSONObject = structureJSONElement.getAsJsonObject();
					T tObject = constructor.apply(structureJSONObject);
					/*
					 * putIfAbsent fixes the edge case scenario where the constructor initializes
					 * class loading of the keyed object. During this initialization, the
					 * loadIfEmpty method might be triggered again, leading to potential duplicate
					 * instances of each keyed object. By using putIfAbsent, we ensure that only one
					 * instance of each keyed object is added to the map, preventing duplicates.
					 */
					keyedMap.putIfAbsent(tObject.getKey(), tObject);
				}
			} catch (ExceptionInInitializerError e)
			{
				throw new IncompatiblePaperVersionException(e);
			}
		}
	}

	@Nullable
	private static String getPlural(RegistryKey<?> key)
	{
		if (key == null)
		{
			return null;
		}
		return key.key().value() + "s";
	}
}
