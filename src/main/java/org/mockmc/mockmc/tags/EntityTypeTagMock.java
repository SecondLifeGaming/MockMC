package org.mockmc.mockmc.tags;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mock implementation of an entity type tag.
 * @mockmc.version 1.21-1.0.0
 */
public class EntityTypeTagMock extends BaseTagMock<EntityType>
{

	public static EntityTypeTagMock from(@NotNull NamespacedKey key, @NotNull JsonArray values)
	{
		List<EntityType> entityTypes = new ArrayList<>();
		for (JsonElement element : values)
		{
			Preconditions.checkState(element.isJsonPrimitive(), "The value is not a primitive value");
			JsonPrimitive primitiveElement = element.getAsJsonPrimitive();
			Preconditions.checkState(primitiveElement.isString(), "The value is not a string value");

			NamespacedKey minecraftKey = NamespacedKey.fromString(primitiveElement.getAsString());
			Preconditions.checkArgument(minecraftKey != null, "The value is not a valid namespaced key");
			entityTypes.add(Bukkit.getRegistry(EntityType.class).get(minecraftKey));
		}

		return new EntityTypeTagMock(key, entityTypes);
	}

	public EntityTypeTagMock(@NotNull NamespacedKey key, @NotNull Collection<EntityType> values)
	{
		super(key, values);
	}

	public EntityTypeTagMock(@NotNull NamespacedKey key, @NotNull EntityType... entityTypes)
	{
		super(key, entityTypes);
	}

}
