package org.mockmc.mockmc.entity;

import org.bukkit.entity.Monster;
import org.bukkit.entity.SpawnCategory;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Monster}.
 *
 * @see CreatureMock
 */
public abstract class MonsterMock extends CreatureMock
		implements
			Monster,
			org.mockmc.mockmc.generated.org.bukkit.entity.MonsterBaseMock
{

	/**
	 * Constructs a new {@link MonsterMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected MonsterMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public SpawnCategory getSpawnCategory()
	{
		return SpawnCategory.MONSTER;
	}
}
