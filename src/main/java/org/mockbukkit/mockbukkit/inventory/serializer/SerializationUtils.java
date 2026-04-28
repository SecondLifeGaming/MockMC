package org.mockbukkit.mockbukkit.inventory.serializer;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@UtilityClass
@SuppressWarnings(
{"deprecation", "unchecked"})
public class SerializationUtils
{
	public static @NotNull Map<String, Object> serialize(
			@NotNull org.bukkit.configuration.serialization.ConfigurationSerializable configurationSerializable)
	{
		Map<String, Object> serialized = new java.util.HashMap<>(configurationSerializable.serialize());
		serialized.put(org.bukkit.configuration.serialization.ConfigurationSerialization.SERIALIZED_TYPE_KEY,
				org.bukkit.configuration.serialization.ConfigurationSerialization
						.getAlias(configurationSerializable.getClass()));
		return serialized;
	}

	public static @NotNull List<Map<String, Object>> serialize(
			@NotNull Collection<? extends org.bukkit.configuration.serialization.ConfigurationSerializable> configurationSerializables)
	{
		return configurationSerializables.stream().map(SerializationUtils::serialize).toList();
	}

	public static ItemStack deserialize(@NotNull Object args)
	{
		Preconditions.checkArgument(args instanceof Map<?, ?>, "Args is not a Map");

		return Bukkit.getUnsafe().deserializeStack((Map<String, Object>) args);
	}

	public static List<ItemStack> deserialize(@NotNull Collection<Object> args)
	{
		return args.stream().map(SerializationUtils::deserialize).toList();
	}

	public static GsonBuilder getDefaultBuilder()
	{
		return new GsonBuilder().registerTypeHierarchyAdapter(ConfigurationSerializable.class,
				new ConfigurationTypeAdapter());
	}

	public static Gson createDefaultBuilder()
	{
		return getDefaultBuilder().create();
	}

}
