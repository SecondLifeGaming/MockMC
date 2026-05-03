package org.mockmc.mockmc.entity;

import org.bukkit.entity.Breeze;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Breeze}.
 *
 * @see MonsterMock
 */
public class BreezeMock extends MonsterMock
		implements
			Breeze,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.BreezeBaseMock
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
	public BreezeMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.BREEZE;
	}
}
