package org.mockmc.mockmc.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Keyed;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.exception.InternalDataLoadException;
import org.mockmc.mockmc.exception.ReflectionAccessException;
import org.mockmc.mockmc.exception.UnimplementedOperationException;
import org.mockmc.mockmc.util.ResourceLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class RegistryAccessMock implements RegistryAccess
{

	private final Map<RegistryKey<?>, Registry<?>> registries = new HashMap<>();
	private static final BiMap<RegistryKey<?>, String> CLASS_NAME_KEY_MAP = createClassToKeyConversions();

	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	public @Nullable <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> type)
	{
		RegistryKey<T> registryKey = determineRegistryKeyFromClass(type);
		if (registryKey == null)
		{
			return findSimpleRegistry(type);
		}
		return getRegistry(registryKey);
	}

	private <T extends Keyed> RegistryKey<T> determineRegistryKeyFromClass(@NotNull Class<T> type)
	{
		return (RegistryKey<T>) CLASS_NAME_KEY_MAP.inverse().get(type.getName());
	}

	@Override
	public @NotNull <T extends Keyed> Registry<T> getRegistry(@NotNull RegistryKey<T> registryKey)
	{
		if (registries.containsKey(registryKey))
		{
			return (Registry<T>) registries.get(registryKey);
		}
		Registry<T> registry = createRegistry(registryKey);
		registries.put(registryKey, registry);
		return registry;
	}

	private static <T extends Keyed> Registry<T> createRegistry(RegistryKey<T> key)
	{
		// Attempt to find the registry in Bukkit's Registry class first
		try
		{
			return findSimpleRegistry((Class<T>) getClass(CLASS_NAME_KEY_MAP.get(key)));
		} catch (Exception e)
		{
			// Fallback to a generic RegistryMock if not found in Bukkit's Registry
			return new RegistryMock<>(key);
		}
	}

	private static Class<?> getClass(String className)
	{
		try
		{
			return Class.forName(className);
		} catch (ClassNotFoundException e)
		{
			throw new InternalDataLoadException(e);
		}
	}

	private static boolean genericTypeMatches(Field a, Class<?> tClass)
	{
		if (a.getGenericType() instanceof ParameterizedType type)
		{
			return type.getActualTypeArguments()[0].equals(tClass);
		}
		return false;
	}

	private static Registry<?> getValue(Field a)
	{
		try
		{
			return (Registry<?>) a.get(null);
		} catch (IllegalAccessException e)
		{
			throw new ReflectionAccessException(
					"Could not access field " + a.getDeclaringClass().getSimpleName() + "." + a.getName());
		}
	}

	private static @NotNull BiMap<RegistryKey<?>, String> createClassToKeyConversions()
	{
		String fileName = "/registries/registry_key_class_relation.json";
		JsonObject object = ResourceLoader.loadResource(fileName).getAsJsonObject();

		BiMap<RegistryKey<?>, String> output = HashBiMap.create();
		for (RegistryKey<?> registryKey : getAllKeys())
		{
			JsonElement element = object.get(registryKey.key().asString());
			if (element != null)
			{
				String className = element.getAsString();
				output.put(registryKey, className);
			} else
			{
				throw new InternalDataLoadException("Null JSON element while retrieving `"
						+ registryKey.key().asString() + "` - MockMC / MC version mismatch?");
			}
		}
		return output;
	}

	private static List<RegistryKey<?>> getAllKeys()
	{
		List<RegistryKey<?>> output = new ArrayList<>();
		for (final Field field : RegistryKey.class.getFields())
		{
			if (field.getType() == RegistryKey.class)
			{
				try
				{
					output.add((RegistryKey<?>) field.get(null));
				} catch (IllegalAccessException e)
				{
					throw new InternalDataLoadException(e);
				}
			}
		}
		return output;
	}

	private static <T extends Keyed> Registry<T> findSimpleRegistry(Class<T> tClass)
	{
		return (Registry<T>) Stream.of(Registry.class.getDeclaredFields())
				.filter(a -> Registry.class.isAssignableFrom(a.getType()))
				.filter(a -> Modifier.isPublic(a.getModifiers())).filter(a -> Modifier.isStatic(a.getModifiers()))
				.filter(a -> genericTypeMatches(a, tClass)).map(RegistryAccessMock::getValue).filter(Objects::nonNull)
				.findAny()
				.orElseThrow(() -> new UnimplementedOperationException("Could not find registry for " + tClass));
	}

}
