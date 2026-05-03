package org.mockmc.mockmc.registry;

import com.google.gson.JsonObject;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class GenericKeyedMock implements Keyed
{

	private final NamespacedKey key;

	public GenericKeyedMock(@NotNull NamespacedKey key)
	{
		this.key = key;
	}

	@Override
	public @NotNull NamespacedKey getKey()
	{
		return this.key;
	}

	public static GenericKeyedMock from(@NotNull JsonObject jsonObject)
	{
		return new GenericKeyedMock(NamespacedKey.fromString(jsonObject.get("key").getAsString()));
	}

}
