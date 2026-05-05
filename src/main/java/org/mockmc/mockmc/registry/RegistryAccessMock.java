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
import io.papermc.paper.registry.tag.Tag;
import org.bukkit.NamespacedKey;
import org.mockmc.mockmc.util.ResourceLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class RegistryAccessMock implements RegistryAccess
{

	private static final Logger LOGGER = Logger.getLogger(RegistryAccessMock.class.getName());

	private final Map<RegistryKey<?>, Registry<?>> registries = new ConcurrentHashMap<>();
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
		Registry<T> registry = (Registry<T>) registries.get(registryKey);
		if (registry == null)
		{
			registry = new LazyRegistry<T>(registryKey);
			registries.put(registryKey, registry);
		}
		return registry;
	}

	private class LazyRegistry<T extends Keyed> implements Registry<T>
	{
		private final RegistryKey<T> key;
		private Registry<T> delegate;

		public LazyRegistry(RegistryKey<T> key)
		{
			this.key = key;
		}

		private Registry<T> getDelegate()
		{
			if (delegate == null)
			{
				String className = CLASS_NAME_KEY_MAP.get(key);
				Registry<T> placeholder = new RegistryMock<>(key, className);
				delegate = placeholder;
				try
				{
					Registry<T> newDelegate = createRegistry(key);
					if (newDelegate != this)
					{
						delegate = newDelegate;
					}
				} catch (Exception _)
				{
					// keep placeholder
				}
			}
			return delegate;
		}

		@Override
		public @Nullable T get(@NotNull NamespacedKey namespacedKey)
		{
			return getDelegate().get(namespacedKey);
		}

		@Override
		public @Nullable T get(@NotNull net.kyori.adventure.key.Key key)
		{
			return getDelegate().get(key);
		}

		@Override
		public @Nullable T get(@NotNull io.papermc.paper.registry.TypedKey<T> key)
		{
			return getDelegate().get(key);
		}

		@Override
		public @NotNull T getOrThrow(@NotNull NamespacedKey namespacedKey)
		{
			return getDelegate().getOrThrow(namespacedKey);
		}

		@Override
		public @NotNull T getOrThrow(@NotNull net.kyori.adventure.key.Key key)
		{
			return getDelegate().getOrThrow(key);
		}

		@Override
		public @NotNull T getOrThrow(@NotNull io.papermc.paper.registry.TypedKey<T> key)
		{
			return getDelegate().getOrThrow(key);
		}

		@Override
		public @Nullable NamespacedKey getKey(@NotNull T t)
		{
			return getDelegate().getKey(t);
		}

		@Override
		public @NotNull NamespacedKey getKeyOrThrow(@NotNull T t)
		{
			return getDelegate().getKeyOrThrow(t);
		}

		@Override
		public @NotNull java.util.stream.Stream<T> stream()
		{
			return getDelegate().stream();
		}

		@Override
		public @NotNull java.util.stream.Stream<NamespacedKey> keyStream()
		{
			return getDelegate().keyStream();
		}

		@Override
		public @NotNull java.util.Iterator<T> iterator()
		{
			return getDelegate().iterator();
		}

		@Override
		public int size()
		{
			return getDelegate().size();
		}

		@Override
		public @Nullable T match(@NotNull String s)
		{
			return getDelegate().match(s);
		}

		@Override
		public boolean hasTag(@NotNull io.papermc.paper.registry.tag.TagKey<T> tagKey)
		{
			return getDelegate().hasTag(tagKey);
		}

		@Override
		public @Nullable io.papermc.paper.registry.tag.Tag<T> getTag(
				@NotNull io.papermc.paper.registry.tag.TagKey<T> tagKey)
		{
			return getDelegate().getTag(tagKey);
		}

		@Override
		public @NotNull java.util.Collection<Tag<T>> getTags()
		{
			return getDelegate().getTags();
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
			String className = CLASS_NAME_KEY_MAP.get(key);
			return new RegistryMock<>(key, className);
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
			String keyString = registryKey.key().asString();
			JsonElement element = object.get(keyString);
			if (element != null)
			{
				String className = element.getAsString();
				output.put(registryKey, className);
			} else
			{
				LOGGER.warning(() -> "Null JSON element while retrieving `" + registryKey.key().asString()
						+ "` (key enum name: " + registryKey.toString() + ") - MockMC / MC version mismatch?");
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
