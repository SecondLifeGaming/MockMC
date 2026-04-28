package org.mockbukkit.mockbukkit.entity;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Blaze}.
 *
 * @see MonsterMock
 */
public class BlazeMock extends MonsterMock
		implements
			Blaze,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.BlazeBaseMock
{

	/**
	 * Constructs a new {@link BlazeMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public BlazeMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.BLAZE;
	}
}
