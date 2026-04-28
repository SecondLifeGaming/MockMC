package org.mockbukkit.mockbukkit.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Giant}.
 *
 * @see MonsterMock
 */
public class GiantMock extends MonsterMock
		implements
			Giant,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.GiantBaseMock
{

	/**
	 * Constructs a new {@link GiantMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public GiantMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.GIANT;
	}
}
