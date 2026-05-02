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

@SuppressWarnings("unchecked")
public class RegistryAccessMock implements RegistryAccess
{

	private final Map<RegistryKey<?>, Registry<?>> registries = new HashMap<>();
	private static final BiMap<RegistryKey<?>, String> CLASS_NAME_KEY_MAP = createClassToKeyConversions();

	/**
	 * @deprecated Use {@link #getRegistry(RegistryKey)} instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	public @Nullable <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> type)
	{
		RegistryKey<T> registryKey = determineRegistryKeyFromClass(type);
		if (registryKey == null)
		{
			return findSimpleRegistry(type.getName());
		}
		return getRegistry(registryKey);
	}

	private static final java.util.Map<Class<?>, RegistryKey<?>> CLASS_TO_KEY_CACHE = new java.util.concurrent.ConcurrentHashMap<>();

	private <T extends Keyed> RegistryKey<T> determineRegistryKeyFromClass(@NotNull Class<T> type)
	{
		return (RegistryKey<T>) CLASS_TO_KEY_CACHE.computeIfAbsent(type,
				t -> (RegistryKey<T>) CLASS_NAME_KEY_MAP.inverse().get(t.getName()));
	}

	@Override
	public @NotNull <T extends Keyed> Registry<T> getRegistry(@NotNull RegistryKey<T> registryKey)
	{
		if (registries.containsKey(registryKey))
		{
			return (Registry<T>) registries.get(registryKey);
		}
		// Put a placeholder to prevent infinite recursion
		Registry<T> placeholder = new RegistryMock<>(registryKey);
		registries.put(registryKey, placeholder);

		try
		{
			Registry<T> registry = createRegistry(registryKey);
			registries.put(registryKey, registry);
			return registry;
		} catch (Exception _)
		{
			return placeholder;
		}
	}

	private static <T extends Keyed> Registry<T> createRegistry(RegistryKey<T> key)
	{
		// Attempt to find the registry in Bukkit's Registry class first
		try
		{
			String className = CLASS_NAME_KEY_MAP.get(key);
			return findSimpleRegistry(className);
		} catch (Exception _)
		{
			// Fallback to a generic RegistryMock if not found in Bukkit's Registry
			return new RegistryMock<>(key);
		}
	}

	private static Registry<?> getValue(Field a)
	{
		try
		{
			return (Registry<?>) a.get(null);
		} catch (IllegalAccessException _)
		{
			throw new ReflectionAccessException(
					"Could not access field " + a.getDeclaringClass().getSimpleName() + "." + a.getName());
		}
	}

	private static final String REGISTRY_RELATION_FILE = "/registries/registry_key_class_relation.json";

	private static @NotNull BiMap<RegistryKey<?>, String> createClassToKeyConversions()
	{
		JsonObject object = ResourceLoader.loadResource(REGISTRY_RELATION_FILE).getAsJsonObject();

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
				} catch (IllegalAccessException _)
				{
					throw new InternalDataLoadException("Could not access RegistryKey field");
				}
			}
		}
		return output;
	}

	private static final Map<String, Registry<?>> SIMPLE_REGISTRY_CACHE = new HashMap<>();

	private static <T extends Keyed> Registry<T> findSimpleRegistry(String targetClassName)
	{
		if (SIMPLE_REGISTRY_CACHE.isEmpty())
		{
			populateSimpleRegistryCache();
		}

		Registry<T> registry = (Registry<T>) SIMPLE_REGISTRY_CACHE.get(targetClassName);
		if (registry == null)
		{
			throw new UnimplementedOperationException("Could not find registry for " + targetClassName);
		}
		return registry;
	}

	private static void populateSimpleRegistryCache()
	{
		for (Field field : Registry.class.getDeclaredFields())
		{
			if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())
					&& Registry.class.isAssignableFrom(field.getType())
					&& field.getGenericType() instanceof ParameterizedType type)
			{
				String typeName = type.getActualTypeArguments()[0].getTypeName();
				Registry<?> registry = getValue(field);
				if (registry != null)
				{
					SIMPLE_REGISTRY_CACHE.put(typeName, registry);
				}
			}
		}
	}

}
