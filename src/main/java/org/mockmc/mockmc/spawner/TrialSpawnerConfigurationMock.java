package org.mockmc.mockmc.spawner;

import org.bukkit.loot.LootTable;
import org.bukkit.spawner.TrialSpawnerConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock implementation of a {@link TrialSpawnerConfiguration}.
 *
 * @mockmc.version 1.21-1.0.0
 */
public class TrialSpawnerConfigurationMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.spawner.TrialSpawnerConfigurationBaseMock
{

	private float baseSpawnsBeforeCooldown = 0.0f;
	private float additionalSpawnsBeforeCooldown = 0.0f;
	private float baseSimultaneousEntities = 0.0f;
	private float additionalSimultaneousEntities = 0.0f;
	private final Map<LootTable, Integer> possibleRewards = new HashMap<>();

	@Override
	public float getBaseSpawnsBeforeCooldown()
	{
		return this.baseSpawnsBeforeCooldown;
	}

	@Override
	public void setBaseSpawnsBeforeCooldown(float baseSpawnsBeforeCooldown)
	{
		this.baseSpawnsBeforeCooldown = baseSpawnsBeforeCooldown;
	}

	@Override
	public float getAdditionalSpawnsBeforeCooldown()
	{
		return this.additionalSpawnsBeforeCooldown;
	}

	@Override
	public void setAdditionalSpawnsBeforeCooldown(float additionalSpawnsBeforeCooldown)
	{
		this.additionalSpawnsBeforeCooldown = additionalSpawnsBeforeCooldown;
	}

	@Override
	public float getBaseSimultaneousEntities()
	{
		return this.baseSimultaneousEntities;
	}

	@Override
	public void setBaseSimultaneousEntities(float baseSimultaneousEntities)
	{
		this.baseSimultaneousEntities = baseSimultaneousEntities;
	}

	@Override
	public float getAdditionalSimultaneousEntities()
	{
		return this.additionalSimultaneousEntities;
	}

	@Override
	public void setAdditionalSimultaneousEntities(float additionalSimultaneousEntities)
	{
		this.additionalSimultaneousEntities = additionalSimultaneousEntities;
	}

	@Override
	public void addPossibleReward(@NotNull LootTable lootTable, int weight)
	{
		this.possibleRewards.put(lootTable, weight);
	}

	@Override
	public void removePossibleReward(@NotNull LootTable lootTable)
	{
		this.possibleRewards.remove(lootTable);
	}

	@Override
	public void setPossibleRewards(@NotNull Map<LootTable, Integer> rewards)
	{
		this.possibleRewards.clear();
		this.possibleRewards.putAll(rewards);
	}

	@Override
	@NotNull
	public Map<LootTable, Integer> getPossibleRewards()
	{
		return Collections.unmodifiableMap(this.possibleRewards);
	}

	// BaseSpawner methods
	private org.bukkit.entity.EntityType spawnedType = org.bukkit.entity.EntityType.PIG;
	private int delay = 0;
	private int spawnRange = 4;
	private int requiredPlayerRange = 16;

	@Override
	public org.bukkit.entity.EntityType getSpawnedType()
	{
		return this.spawnedType;
	}

	@Override
	public void setSpawnedType(org.bukkit.entity.EntityType spawnedType)
	{
		this.spawnedType = spawnedType;
	}

	@Override
	public int getDelay()
	{
		return this.delay;
	}

	@Override
	public void setDelay(int delay)
	{
		this.delay = delay;
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
	public int getRequiredPlayerRange()
	{
		return this.requiredPlayerRange;
	}

	@Override
	public void setRequiredPlayerRange(int requiredPlayerRange)
	{
		this.requiredPlayerRange = requiredPlayerRange;
	}
}
