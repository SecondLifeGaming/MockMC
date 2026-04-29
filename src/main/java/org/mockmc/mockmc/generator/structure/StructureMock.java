package org.mockmc.mockmc.generator.structure;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class StructureMock extends Structure
		implements
			org.mockmc.mockmc.generated.org.bukkit.generator.structure.GeneratedStructureBaseMock
{

	private final NamespacedKey key;

	private final StructureType type;

	/**
	 * @param key
	 *            The namespaced key representing this structure
	 * @param type
	 *            The type of structure
	 */
	public StructureMock(NamespacedKey key, StructureType type)
	{
		this.key = key;
		this.type = type;
	}

	/**
	 * @param data
	 *            Json data
	 * @deprecated Will use {@link #StructureMock(NamespacedKey, StructureType)}
	 *             instead.
	 */
	@Deprecated(forRemoval = true)
	public StructureMock(JsonObject data)
	{
		Preconditions.checkNotNull(data);
		this.key = NamespacedKey.fromString(data.get("key").getAsString());
		this.type = Registry.STRUCTURE_TYPE
				.get(Preconditions.checkNotNull(NamespacedKey.fromString(data.get("type").getAsString())));
	}

	@ApiStatus.Internal
	public static StructureMock from(JsonObject data)
	{
		Preconditions.checkNotNull(data);
		Preconditions.checkArgument(data.has("key"), "Missing json key");
		Preconditions.checkArgument(data.has("type"), "Missing json key");
		NamespacedKey key = NamespacedKey.fromString(data.get("key").getAsString());
		StructureType type = Registry.STRUCTURE_TYPE
				.get(Preconditions.checkNotNull(NamespacedKey.fromString(data.get("type").getAsString())));
		return new StructureMock(key, type);
	}

	@Override
	@NotNull
	public StructureType getStructureType()
	{
		return type;
	}

	@Override
	@NotNull
	public NamespacedKey getKey()
	{
		return key;
	}
}
