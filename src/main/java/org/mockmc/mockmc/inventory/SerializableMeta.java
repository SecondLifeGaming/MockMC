package org.mockmc.mockmc.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.meta.ItemMeta;
import org.mockmc.mockmc.exception.ItemSerializationException;
import org.mockmc.mockmc.inventory.meta.ArmorMetaMock;
import org.mockmc.mockmc.inventory.meta.ArmorStandMetaMock;
import org.mockmc.mockmc.inventory.meta.AxolotlBucketMetaMock;
import org.mockmc.mockmc.inventory.meta.BannerMetaMock;
import org.mockmc.mockmc.inventory.meta.BlockStateMetaMock;
import org.mockmc.mockmc.inventory.meta.BookMetaMock;
import org.mockmc.mockmc.inventory.meta.BundleMetaMock;
import org.mockmc.mockmc.inventory.meta.ColorableArmorMetaMock;
import org.mockmc.mockmc.inventory.meta.CompassMetaMock;
import org.mockmc.mockmc.inventory.meta.CrossbowMetaMock;
import org.mockmc.mockmc.inventory.meta.EnchantmentStorageMetaMock;
import org.mockmc.mockmc.inventory.meta.FireworkEffectMetaMock;
import org.mockmc.mockmc.inventory.meta.FireworkMetaMock;
import org.mockmc.mockmc.inventory.meta.ItemMetaMock;
import org.mockmc.mockmc.inventory.meta.KnowledgeBookMetaMock;
import org.mockmc.mockmc.inventory.meta.LeatherArmorMetaMock;
import org.mockmc.mockmc.inventory.meta.MapMetaMock;
import org.mockmc.mockmc.inventory.meta.OminousBottleMetaMock;
import org.mockmc.mockmc.inventory.meta.PotionMetaMock;
import org.mockmc.mockmc.inventory.meta.ShieldMetaMock;
import org.mockmc.mockmc.inventory.meta.SkullMetaMock;
import org.mockmc.mockmc.inventory.meta.SpawnEggMetaMock;
import org.mockmc.mockmc.inventory.meta.SuspiciousStewMetaMock;
import org.mockmc.mockmc.inventory.meta.TropicalFishBucketMetaMock;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

@SerializableAs("ItemMeta")
public class SerializableMeta implements ConfigurationSerializable
{

	private static final String TYPE_FIELD = "meta-type";

	private static final ImmutableMap<Class<? extends ItemMetaMock>, String> classMap = ImmutableMap
			.<Class<? extends ItemMetaMock>, String>builder().put(ArmorMetaMock.class, "ARMOR")
			.put(ArmorStandMetaMock.class, "ARMOR_STAND").put(BannerMetaMock.class, "BANNER")
			.put(BlockStateMetaMock.class, "TILE_ENTITY").put(BookMetaMock.class, "BOOK")
			// .put(BookSignedMetaMock.class, "BOOK_SIGNED")
			.put(SkullMetaMock.class, "SKULL").put(LeatherArmorMetaMock.class, "LEATHER_ARMOR")
			.put(ColorableArmorMetaMock.class, "COLORABLE_ARMOR").put(MapMetaMock.class, "MAP")
			.put(PotionMetaMock.class, "POTION").put(ShieldMetaMock.class, "SHIELD")
			.put(SpawnEggMetaMock.class, "SPAWN_EGG").put(EnchantmentStorageMetaMock.class, "ENCHANTED")
			.put(FireworkMetaMock.class, "FIREWORK").put(FireworkEffectMetaMock.class, "FIREWORK_EFFECT")
			.put(KnowledgeBookMetaMock.class, "KNOWLEDGE_BOOK")
			.put(TropicalFishBucketMetaMock.class, "TROPICAL_FISH_BUCKET")
			.put(AxolotlBucketMetaMock.class, "AXOLOTL_BUCKET").put(CrossbowMetaMock.class, "CROSSBOW")
			.put(SuspiciousStewMetaMock.class, "SUSPICIOUS_STEW")
			// .put(EntityTagMetaMock.class, "ENTITY_TAG")
			.put(CompassMetaMock.class, "COMPASS").put(BundleMetaMock.class, "BUNDLE")
			// .put(MusicInstrumentMetaMock.class, "MUSIC_INSTRUMENT")
			.put(OminousBottleMetaMock.class, "OMINOUS_BOTTLE").put(ItemMetaMock.class, "UNSPECIFIC").build();
	private static final ImmutableMap<String, Function<Map<String, Object>, ? extends ItemMetaMock>> factoryMap = compileFactoryMap();

	private static ImmutableMap<String, Function<Map<String, Object>, ? extends ItemMetaMock>> compileFactoryMap()
	{
		ImmutableMap.Builder<String, Function<Map<String, Object>, ? extends ItemMetaMock>> builder = ImmutableMap
				.builder();

		for (Map.Entry<Class<? extends ItemMetaMock>, String> mapping : SerializableMeta.classMap.entrySet())
		{
			builder.put(mapping.getValue(), createDeserializer(mapping.getKey()));
		}
		return builder.build();
	}

	private static Function<Map<String, Object>, ? extends ItemMetaMock> createDeserializer(
			Class<? extends ItemMetaMock> clazz)
	{
		Function<Map<String, Object>, ? extends ItemMetaMock> fromDeserialize = findMethodDeserializer(clazz,
				"deserialize", "deserialize");
		if (fromDeserialize != null)
		{
			return fromDeserialize;
		}

		Function<Map<String, Object>, ? extends ItemMetaMock> fromValueOf = findMethodDeserializer(clazz, "valueOf",
				"valueOf");
		if (fromValueOf != null)
		{
			return fromValueOf;
		}

		Function<Map<String, Object>, ? extends ItemMetaMock> fromConstructor = findConstructorDeserializer(clazz);
		if (fromConstructor != null)
		{
			return fromConstructor;
		}

		String message = String.format(
				"""
						There is no deserialization method available for class %s. Make sure that the class follows the
						rules of class org.bukkit.configuration.serialization.ConfigurationSerializable.

						To be compliant it should have one of the following:
						- A static method "deserialize" that accepts a single Map<String, Object> and returns the class instance.
						- A static method "valueOf" that accepts a single Map<String, Object> and returns the class instance.
						- A constructor that accepts a single Map<String, Object>.
						""",
				clazz.getName());
		throw new UnsupportedOperationException(message);
	}

	private static Function<Map<String, Object>, ? extends ItemMetaMock> findMethodDeserializer(
			Class<? extends ItemMetaMock> clazz, String methodName, String operationName)
	{
		try
		{
			Method method = clazz.getDeclaredMethod(methodName, Map.class);
			return a ->
			{
				try
				{
					return (ItemMetaMock) method.invoke(null, a);
				} catch (IllegalAccessException | InvocationTargetException e)
				{
					throw new ItemSerializationException("Error while calling " + operationName + " method.", e);
				}
			};
		} catch (NoSuchMethodException e)
		{
			return null;
		}
	}

	private static Function<Map<String, Object>, ? extends ItemMetaMock> findConstructorDeserializer(
			Class<? extends ItemMetaMock> clazz)
	{
		try
		{
			Constructor<? extends ItemMetaMock> constructor = clazz.getDeclaredConstructor(Map.class);
			return a ->
			{
				try
				{
					return constructor.newInstance(a);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
				{
					throw new ItemSerializationException("Error while calling deserialize constructor.", e);
				}
			};
		} catch (NoSuchMethodException e)
		{
			return null;
		}
	}

	private SerializableMeta()
	{
	}

	public static ItemMeta deserialize(Map<String, Object> map)
	{
		Preconditions.checkArgument(map != null, "Cannot deserialize null map");

		String type = SerializableMeta.getString(map, SerializableMeta.TYPE_FIELD, false);
		Function<Map<String, Object>, ? extends ItemMetaMock> constructor = SerializableMeta.factoryMap.get(type);

		if (constructor == null)
		{
			throw new IllegalArgumentException(type + " is not a valid " + SerializableMeta.TYPE_FIELD);
		}

		return constructor.apply(map);
	}

	@Override
	public Map<String, Object> serialize()
	{
		throw new AssertionError();
	}

	public static String getString(Map<?, ?> map, Object field, boolean nullable)
	{
		return SerializableMeta.getObject(String.class, map, field, nullable);
	}

	public static boolean getBoolean(Map<?, ?> map, Object field)
	{
		Boolean value = getObject(Boolean.class, map, field, true);
		return value != null && value;
	}

	public static <T> T getObject(Class<T> clazz, Map<?, ?> map, Object field, boolean nullable)
	{
		final Object object = map.get(field);

		if (clazz.isInstance(object))
		{
			return clazz.cast(object);
		}

		// SPIGOT-7675 - More lenient conversion of floating point numbers from other
		// number types:
		if (clazz == Float.class || clazz == Double.class)
		{
			if (object instanceof Number number)
			{
				if (clazz == Float.class)
				{
					return clazz.cast(number.floatValue());
				} else
				{
					return clazz.cast(number.doubleValue());
				}
			}
		}

		if (object == null)
		{
			if (!nullable)
			{
				throw new NoSuchElementException(map + " does not contain " + field);
			}
			return null;
		}
		throw new IllegalArgumentException(field + "(" + object + ") is not a valid " + clazz);
	}

	public static <T> List<T> getList(Class<T> clazz, Map<?, ?> map, Object field)
	{
		List<T> result = new ArrayList<>();
		List<?> list = getObject(List.class, map, field, true);
		if (list == null || list.isEmpty())
		{
			return result;
		}
		for (Object object : list)
		{
			T cast = castListElement(clazz, object);
			if (cast != null)
			{
				result.add(cast);
			}
		}
		return result;
	}

	private static <T> T castListElement(Class<T> clazz, Object object)
	{
		if (clazz.isInstance(object))
		{
			return clazz.cast(object);
		}
		if (object instanceof Number number)
		{
			if (clazz == Float.class)
			{
				return clazz.cast(number.floatValue());
			}
			if (clazz == Double.class)
			{
				return clazz.cast(number.doubleValue());
			}
		}
		return null;
	}

}
