package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.HappyGhast;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link HappyGhast}.
 *
 * @see AnimalsMock
 */
public class HappyGhastMock extends AnimalsMock
		implements
			HappyGhast,
			org.mockmc.mockmc.generated.org.bukkit.entity.HappyGhastBaseMock
{

	/**
	 * Constructs a new {@link HappyGhast} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public HappyGhastMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.HAPPY_GHAST;
	}
}
