package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link SpawnerMinecart}.
 *
 * @see MinecartMock
 */
public class SpawnerMinecartMock extends MinecartMock
		implements
			SpawnerMinecart,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.minecart.SpawnerMinecartBaseMock
{

	private int spawnCount = 4;

	private int spawnDelay = 20;

	private int spawnRange = 4;

	private int minSpawnDelay = 200;

	private int maxSpawnDelay = 800;

	private int requiredPlayerRange = 16;

	private int maxNearbyEntities = 6;

	@Nullable
	private EntityType spawnerType;

	/**
	 * Constructs a new {@link SpawnerMinecartMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public SpawnerMinecartMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public Material getMinecartMaterial()
	{
		return Material.MINECART;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SPAWNER_MINECART;
	}

	@Override
	@Nullable
	public EntityType getSpawnedType()
	{
		return this.spawnerType;
	}

	@Override
	public void setSpawnedType(@Nullable EntityType entityType)
	{
		Preconditions.checkArgument(entityType != EntityType.UNKNOWN, "Can't spawn EntityType %s from mob spawners!",
				entityType);
		this.spawnerType = entityType;
	}

	@Override
	public int getDelay()
	{
		return this.spawnDelay;
	}

	@Override
	public void setDelay(int delay)
	{
		this.spawnDelay = delay;
	}

	@Override
	public int getMinSpawnDelay()
	{
		return this.minSpawnDelay;
	}

	@Override
	public void setMinSpawnDelay(int spawnDelay)
	{
		Preconditions.checkArgument(spawnDelay <= this.getMaxSpawnDelay(),
				"Minimum Spawn Delay must be less than or equal to Maximum Spawn Delay");
		this.minSpawnDelay = spawnDelay;
	}

	@Override
	public int getMaxSpawnDelay()
	{
		return this.maxSpawnDelay;
	}

	@Override
	public void setMaxSpawnDelay(int spawnDelay)
	{
		Preconditions.checkArgument(spawnDelay > 0, "Maximum Spawn Delay must be greater than 0.");
		Preconditions.checkArgument(spawnDelay >= this.getMinSpawnDelay(),
				"Maximum Spawn Delay must be greater than or equal to Minimum Spawn Delay");
		this.maxSpawnDelay = spawnDelay;
	}

	@Override
	public int getSpawnCount()
	{
		return this.spawnCount;
	}

	@Override
	public void setSpawnCount(int count)
	{
		this.spawnCount = count;
	}

	@Override
	public int getRequiredPlayerRange()
	{
		return this.requiredPlayerRange;
	}

	@Override
	public void setRequiredPlayerRange(int requiredPlayerRange)
	{
		this.requiredPlayerRange = requiredPlayerRange;
	}

	@Override
	public int getSpawnRange()
	{
		return this.spawnRange;
	}

	@Override
	public void setSpawnRange(int spawnRange)
	{
		this.spawnRange = spawnRange;
	}

	@Override
	public int getMaxNearbyEntities()
	{
		return this.maxNearbyEntities;
	}

	@Override
	public void setMaxNearbyEntities(int maxNearbyEntities)
	{
		this.maxNearbyEntities = maxNearbyEntities;
	}
}
