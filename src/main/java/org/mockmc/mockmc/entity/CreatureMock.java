package org.mockmc.mockmc.entity;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Creature}.
 *
 * @see MobMock
 */
public abstract class CreatureMock extends MobMock
		implements
			Creature,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.CreatureBaseMock
{

	/**
	 * Constructs a new {@link CreatureMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected CreatureMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
