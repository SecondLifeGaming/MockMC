package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.TrialSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.spawner.TrialSpawnerConfiguration;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.spawner.TrialSpawnerConfigurationMock;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Mock implementation of a {@link TrialSpawner}.
 *
 * @see TileStateMock
 * @mockmc.version 1.21-1.0.0
 */
public class TrialSpawnerStateMock extends TileStateMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.block.TrialSpawnerBaseMock
{

	private final TrialSpawnerConfiguration normalConfiguration = new TrialSpawnerConfigurationMock();
	private final TrialSpawnerConfiguration ominousConfiguration = new TrialSpawnerConfigurationMock();
	private long cooldownEnd = 0L;
	private int cooldownLength = 3600; // Default 3 minutes in ticks? Wait, 180 seconds * 20 = 3600.
	private long nextSpawnAttempt = 0L;
	private int requiredPlayerRange = 16;
	private boolean ominous = false;
	private final Set<Player> trackedPlayers = new HashSet<>();
	private final Set<Entity> trackedEntities = new HashSet<>();

	protected TrialSpawnerStateMock(@NotNull Block block)
	{
		super(block);
	}

	public TrialSpawnerStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected TrialSpawnerStateMock(@NotNull TrialSpawnerStateMock state)
	{
		super(state);
		this.cooldownEnd = state.cooldownEnd;
		this.cooldownLength = state.cooldownLength;
		this.nextSpawnAttempt = state.nextSpawnAttempt;
		this.requiredPlayerRange = state.requiredPlayerRange;
		this.ominous = state.ominous;
	}

	@Override
	@NotNull
	public TrialSpawnerStateMock getSnapshot()
	{
		return new TrialSpawnerStateMock(this);
	}

	@Override
	@NotNull
	public TrialSpawnerStateMock copy()
	{
		return new TrialSpawnerStateMock(this);
	}

	@Override
	public long getCooldownEnd()
	{
		return this.cooldownEnd;
	}

	@Override
	public void setCooldownEnd(long cooldownEnd)
	{
		this.cooldownEnd = cooldownEnd;
	}

	@Override
	public int getCooldownLength()
	{
		return this.cooldownLength;
	}

	@Override
	public void setCooldownLength(int cooldownLength)
	{
		this.cooldownLength = cooldownLength;
	}

	@Override
	public long getNextSpawnAttempt()
	{
		return this.nextSpawnAttempt;
	}

	@Override
	public void setNextSpawnAttempt(long nextSpawnAttempt)
	{
		this.nextSpawnAttempt = nextSpawnAttempt;
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
	public boolean isOminous()
	{
		return this.ominous;
	}

	@Override
	public void setOminous(boolean ominous)
	{
		this.ominous = ominous;
	}

	@Override
	@NotNull
	public TrialSpawnerConfiguration getNormalConfiguration()
	{
		return this.normalConfiguration;
	}

	@Override
	@NotNull
	public TrialSpawnerConfiguration getOminousConfiguration()
	{
		return this.ominousConfiguration;
	}

	@Override
	@NotNull
	public Collection<Player> getTrackedPlayers()
	{
		return Collections.unmodifiableCollection(this.trackedPlayers);
	}

	@Override
	public boolean isTrackingPlayer(@NotNull Player player)
	{
		return this.trackedPlayers.contains(player);
	}

	@Override
	public void startTrackingPlayer(@NotNull Player player)
	{
		this.trackedPlayers.add(player);
	}

	@Override
	public void stopTrackingPlayer(@NotNull Player player)
	{
		this.trackedPlayers.remove(player);
	}

	@Override
	@NotNull
	public Collection<Entity> getTrackedEntities()
	{
		return Collections.unmodifiableCollection(this.trackedEntities);
	}

	@Override
	public boolean isTrackingEntity(@NotNull Entity entity)
	{
		return this.trackedEntities.contains(entity);
	}

	@Override
	public void startTrackingEntity(@NotNull Entity entity)
	{
		this.trackedEntities.add(entity);
	}

	@Override
	public void stopTrackingEntity(@NotNull Entity entity)
	{
		this.trackedEntities.remove(entity);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof TrialSpawnerStateMock that))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		return cooldownEnd == that.cooldownEnd && cooldownLength == that.cooldownLength && nextSpawnAttempt == that.nextSpawnAttempt && requiredPlayerRange == that.requiredPlayerRange && ominous == that.ominous && java.util.Objects.equals(normalConfiguration, that.normalConfiguration) && java.util.Objects.equals(ominousConfiguration, that.ominousConfiguration) && java.util.Objects.equals(trackedPlayers, that.trackedPlayers) && java.util.Objects.equals(trackedEntities, that.trackedEntities);
	}

	@Override
	public int hashCode()
	{
		return java.util.Objects.hash(super.hashCode(), normalConfiguration, ominousConfiguration, cooldownEnd, cooldownLength, nextSpawnAttempt, requiredPlayerRange, ominous, trackedPlayers, trackedEntities);
	}

}
