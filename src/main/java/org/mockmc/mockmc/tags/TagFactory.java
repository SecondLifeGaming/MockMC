package org.mockmc.mockmc.tags;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.util.ResourceLoader;

public final class TagFactory
{

	@NotNull
	public static Tag<?> createTag(@NotNull TagRegistry registry, @NotNull NamespacedKey key)
	{
		Preconditions.checkNotNull(registry, "registry cannot be null");
		Preconditions.checkNotNull(key, "key cannot be null");

		String path = String.format("/tags/%s/%s.json", registry.getRegistry(), key.value());
		JsonElement root = ResourceLoader.loadResource(path);
		if (root == null || !root.isJsonObject() || !root.getAsJsonObject().has("values"))
		{
			return createEmptyTag(registry, key);
		}
		JsonElement values = root.getAsJsonObject().get("values");

		Preconditions.checkArgument(values.isJsonArray(), "Invalid tag values");
		return createTag(registry, key, values.getAsJsonArray());
	}

	@NotNull
	public static Tag<?> createEmptyTag(@NotNull TagRegistry registry, @NotNull NamespacedKey key)
	{
		Preconditions.checkNotNull(registry, "registry cannot be null");
		Preconditions.checkNotNull(key, "key cannot be null");
		return switch (registry)
		{
			case BLOCKS, ITEMS -> new MaterialTagMock(key, java.util.Collections.emptySet());
			case ENTITY_TYPES -> new EntityTypeTagMock(key, java.util.Collections.emptySet());
			case FLUIDS -> new FluidTagMock(key, java.util.Collections.emptySet());
			case GAME_EVENTS -> new GameEventTagMock(key, java.util.Collections.emptySet());
			case DAMAGE_TYPES -> new DamageTypeTagMock(key, java.util.Collections.emptySet());
		};
	}

	static Tag<?> createTag(@NotNull TagRegistry tagRegistry, @NotNull NamespacedKey key, @NotNull JsonArray values)
	{
		return switch (tagRegistry)
		{
			case BLOCKS, ITEMS -> MaterialTagMock.from(key, values);
			case ENTITY_TYPES -> EntityTypeTagMock.from(key, values);
			case FLUIDS -> FluidTagMock.from(key, values);
			case GAME_EVENTS -> GameEventTagMock.from(key, values);
			case DAMAGE_TYPES -> DamageTypeTagMock.from(key, values);
		};
	}

	private TagFactory()
	{
		throw new IllegalStateException("This is a utility class and should not be initialized");
	}

}
