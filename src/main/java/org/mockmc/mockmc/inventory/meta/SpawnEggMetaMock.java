package org.mockmc.mockmc.inventory.meta;

import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.inventory.SerializableMeta;
import java.util.Map;

/**
 * Mock implementation of an {@link SpawnEggMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class SpawnEggMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.org.bukkit.inventory.meta.SpawnEggMetaBaseMock
{

	/**
	 * Constructs a new {@link SpawnEggMetaMock}.
	 */
	public SpawnEggMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link SpawnEggMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public SpawnEggMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof SpawnEggMeta spawnMeta)
		{
			// TODO cloning logic from spawnMeta
		}
	}

	@Override
	public EntityType getSpawnedType()
	{
		throw new UnsupportedOperationException("Must check item type to get spawned type");
	}

	@Override
	public void setSpawnedType(EntityType type)
	{
		throw new UnsupportedOperationException("Must change item type to set spawned type");
	}

	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public SpawnEggMetaMock clone()
	{
		return new SpawnEggMetaMock(this);
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized SpawnEggMetaMock object in a Map&lt;String,
	 *            Object&gt; format.
	 * @return A new instance of the SpawnEggMetaMock class.
	 */
	@NotNull
	public static SpawnEggMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		SpawnEggMetaMock serialMock = new SpawnEggMetaMock();
		serialMock.deserializeInternal(args);
		return serialMock;
	}

	@Override
	protected String getTypeName()
	{
		return "SPAWN_EGG";
	}
}
