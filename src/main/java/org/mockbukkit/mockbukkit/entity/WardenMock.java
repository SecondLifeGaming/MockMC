package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Warden;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Mock implementation of a {@link Warden}.
 *
 * @see MonsterMock
 */
public class WardenMock extends MonsterMock
		implements
			Warden,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.WardenBaseMock
{

	private final Map<Entity, Integer> angerPerEntity = new HashMap<>();

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
	public int getAnger(@NotNull Entity entity)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		return angerPerEntity.getOrDefault(entity, 0);
	}

	@Override
	public void increaseAnger(@NotNull Entity entity, int increase)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		if (angerPerEntity.containsKey(entity))
		{
			int newAnger = angerPerEntity.get(entity) + increase;
			Preconditions.checkState(newAnger <= 150, "Anger can't be higher than 150");
			angerPerEntity.put(entity, newAnger);
		} else
		{
			Preconditions.checkState(increase <= 150, "Anger can't be higher than 150");
			angerPerEntity.put(entity, increase);
		}
	}

	@Override
	public void setAnger(@NotNull Entity entity, int anger)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		Preconditions.checkArgument(anger <= 150, "Anger can't be higher than 150");
		angerPerEntity.put(entity, anger);
	}

	@Override
	@NotNull
	public AngerLevel getAngerLevel()
	{
		if (getAnger() <= 39)
		{
			return AngerLevel.CALM;
		} else if (getAnger() <= 79)
		{
			return AngerLevel.AGITATED;
		} else
		{
			return AngerLevel.ANGRY;
		}
	}

	@Override
	public EntityType getType()
	{
		return EntityType.WARDEN;
	}
}
