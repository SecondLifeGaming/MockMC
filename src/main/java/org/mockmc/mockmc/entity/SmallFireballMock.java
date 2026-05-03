package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.SmallFireball;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link SmallFireball}.
 *
 * @see SizedFireballMock
 */
public class SmallFireballMock extends SizedFireballMock
		implements
			SmallFireball,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.SmallFireballBaseMock
{

	/**
	 * Constructs a new {@link SmallFireballMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public SmallFireballMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SMALL_FIREBALL;
	}
}
