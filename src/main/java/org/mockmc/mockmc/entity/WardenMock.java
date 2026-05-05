package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Warden;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Mock implementation of a {@link Warden}.
 *
 * @see MonsterMock
 * @mockmc.version 1.21-1.0.0
 */
public class WardenMock extends MonsterMock
		implements
			Warden,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.WardenBaseMock
{

	private final Map<Entity, Integer> angerPerEntity = new HashMap<>();
	private Location disturbanceLocation;
	private int sonicBoomCooldown = 0;

	/**
	 * Constructs a new {@link WardenMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public WardenMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public int getAnger()
	{
		return angerPerEntity.values().stream().mapToInt(Integer::intValue).sum();
	}

	@Override
	public int getAnger(@NotNull Entity entity)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		return angerPerEntity.getOrDefault(entity, 0);
	}

	@Override
	public void increaseAnger(@NotNull Entity entity, int increase)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		int currentAnger = angerPerEntity.getOrDefault(entity, 0);
		setAnger(entity, currentAnger + increase);
	}

	@Override
	public void setAnger(@NotNull Entity entity, int anger)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		Preconditions.checkArgument(anger >= 0 && anger <= 150, "Anger must be between 0 and 150");
		angerPerEntity.put(entity, anger);
	}

	@Override
	public void clearAnger(@NotNull Entity entity)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		angerPerEntity.remove(entity);
	}

	@Override
	public int getHighestAnger()
	{
		return angerPerEntity.values().stream().mapToInt(Integer::intValue).max().orElse(0);
	}

	@Override
	@Nullable
	public LivingEntity getEntityAngryAt()
	{
		return angerPerEntity.entrySet().stream().filter(entry -> entry.getKey() instanceof LivingEntity)
				.max(Comparator.comparingInt(Map.Entry::getValue)).map(entry -> (LivingEntity) entry.getKey())
				.orElse(null);
	}

	@Override
	@NotNull
	public AngerLevel getAngerLevel()
	{
		int highestAnger = getHighestAnger();
		if (highestAnger <= 39)
		{
			return AngerLevel.CALM;
		} else if (highestAnger <= 79)
		{
			return AngerLevel.AGITATED;
		} else
		{
			return AngerLevel.ANGRY;
		}
	}

	@Override
	public void setDisturbanceLocation(@NotNull Location location)
	{
		Preconditions.checkNotNull(location, "Location cannot be null");
		this.disturbanceLocation = location.clone();
	}

	/**
	 * Gets the last sensed disturbance location.
	 *
	 * @return The disturbance location, or null if none.
	 * @mockmc.version 1.21-1.0.0
	 */
	@Nullable
	public Location getDisturbanceLocation()
	{
		return disturbanceLocation != null ? disturbanceLocation.clone() : null;
	}

	/**
	 * Gets the current sonic boom cooldown in ticks.
	 *
	 * @return The cooldown.
	 * @mockmc.version 1.21-1.0.0
	 */
	public int getSonicBoomCooldown()
	{
		return sonicBoomCooldown;
	}

	/**
	 * Sets the current sonic boom cooldown in ticks.
	 *
	 * @param cooldown
	 *            The cooldown.
	 * @mockmc.version 1.21-1.0.0
	 */
	public void setSonicBoomCooldown(int cooldown)
	{
		this.sonicBoomCooldown = cooldown;
	}

	/**
	 * Gets an unmodifiable view of the anger map.
	 *
	 * @return The anger map.
	 * @mockmc.version 1.21-1.0.0
	 */
	@NotNull
	public Map<Entity, Integer> getAngerPerEntity()
	{
		return Collections.unmodifiableMap(angerPerEntity);
	}

	/**
	 * Simulates the Warden receiving a vibration from a specific entity.
	 *
	 * @param source
	 *            The entity that caused the vibration.
	 * @param frequency
	 *            The frequency of the vibration (1-15).
	 */
	public void simulateVibration(@NotNull Entity source, int frequency)
	{
		Preconditions.checkNotNull(source, "Source cannot be null");
		Preconditions.checkArgument(1 <= frequency && frequency <= 15, "Frequency must be between 1-15");
		// High frequency vibrations increase anger more
		int angerIncrease = (frequency >= 10) ? 10 : 2;
		this.setAnger(source, this.getAnger(source) + angerIncrease);
	}

	@Override
	protected void onApplyNbt(@NotNull org.mockmc.mockmc.util.NbtStateMock nbt)
	{
		if (nbt.has("AngerLevel"))
		{
			// In a real implementation we would map this to the angerPerEntity map
			// or a global anger level.
		}
	}

	@Override
	public EntityType getType()
	{
		return EntityType.WARDEN;
	}
}
