package org.mockbukkit.metaminer.json;

import com.google.gson.JsonArray;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.set.RegistryKeySet;
import org.jetbrains.annotations.Nullable;

/**
 * Converts a {@link RegistryKeySet} into a {@link JsonArray}.
 */
public class RegistryKeySetElementFactory
{

	/**
	 * Converts a {@link RegistryKeySet} into a {@link JsonArray}.
	 *
	 * @param keySet The key set to be converted.
	 * @return The element
	 */
	@Nullable
	public static JsonArray toJson(@Nullable RegistryKeySet<?> keySet)
	{
		if (keySet == null)
		{
			return null;
		}

		JsonArray jsonElements = new JsonArray();
		for (TypedKey<?> key : keySet.values())
		{
			jsonElements.add(key.key().asString());
		}

		return jsonElements.isEmpty() ? null : jsonElements;
	}

	private RegistryKeySetElementFactory()
	{
		// Hide the public constructor
	}

}
