package org.mockbukkit.mockbukkit.world;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.GameRule;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NullMarked
public class WorldConfiguration
{

	private final String name;
	private final Map<String, JsonElement> gameRules;

	private WorldConfiguration(String name, Map<String, JsonElement> gameRules)
	{
		this.name = name;
		this.gameRules = gameRules;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "WorldConfiguration(name=" + name + ", gameRules=" + gameRules + ")";
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof WorldConfiguration that))
		{
			return false;
		}
		return java.util.Objects.equals(name, that.name) && java.util.Objects.equals(gameRules, that.gameRules);
	}

	@Override
	public int hashCode()
	{
		return java.util.Objects.hash(name, gameRules);
	}

	@Nullable
	public <T> T getDefaultValue(String key, Class<T> type)
	{
		var value = gameRules.get(key);

		if (Boolean.class.isAssignableFrom(type))
		{
			return type.cast(value.getAsBoolean());
		} else if (Integer.class.isAssignableFrom(type))
		{
			return type.cast(value.getAsInt());
		} else
		{
			throw new IllegalArgumentException(
					String.format("The key '%s' is not a instance of %s", key, type.getSimpleName()));
		}
	}

	@Nullable
	public <T> T getDefaultValue(GameRule<T> gameRule)
	{
		return getDefaultValue(gameRule.key().asString(), gameRule.getType());
	}

	public Set<NamespacedKey> getGameRules()
	{
		return this.gameRules.keySet().stream().map(NamespacedKey::fromString).collect(Collectors.toSet());
	}

	public static WorldConfiguration fromJson(JsonObject jsonObject)
	{
		Preconditions.checkNotNull(jsonObject, "The json can't be null");

		var nameField = jsonObject.get("name");
		Preconditions.checkNotNull(nameField, "The 'name' field is null");
		var name = nameField.getAsString();

		var gameRulesField = jsonObject.getAsJsonObject("game_rules");
		Preconditions.checkNotNull(gameRulesField, "The 'game_rules' field is null");
		var gameRules = gameRulesField.asMap();

		return new WorldConfiguration(name, gameRules);
	}

}
