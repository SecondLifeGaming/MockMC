package org.mockmc.mockmc.tags;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.bukkit.Bukkit;
import org.bukkit.Fluid;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mock implementation of a fluid tag.
 *
 * @mockmc.version 1.21-1.0.0
 */
public class FluidTagMock extends BaseTagMock<Fluid>
{

	public static FluidTagMock from(@NotNull NamespacedKey key, @NotNull JsonArray values)
	{
		List<Fluid> fluids = new ArrayList<>();
		for (JsonElement element : values)
		{
			Preconditions.checkState(element.isJsonPrimitive(), "The value is not a primitive value");
			JsonPrimitive primitiveElement = element.getAsJsonPrimitive();
			Preconditions.checkState(primitiveElement.isString(), "The value is not a string value");

			NamespacedKey minecraftKey = NamespacedKey.fromString(primitiveElement.getAsString());
			Preconditions.checkArgument(minecraftKey != null, "The value is not a valid namespaced key");
			Fluid fluid = Bukkit.getRegistry(Fluid.class).get(minecraftKey);
			Preconditions.checkState(fluid != null, "Unknown gameEvent {}", minecraftKey);
			fluids.add(fluid);
		}

		return new FluidTagMock(key, fluids);
	}

	public FluidTagMock(@NotNull NamespacedKey key, @NotNull Collection<Fluid> values)
	{
		super(key, values);
	}

	public FluidTagMock(@NotNull NamespacedKey key, @NotNull Fluid... fluids)
	{
		super(key, fluids);
	}

}
