package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

@SuppressWarnings("java:S110")
public class SquidMock extends AgeableMock implements org.mockmc.mockmc.generated.server.org.bukkit.entity.SquidBaseMock
{

	/**
	 * Constructs a new {@link SquidMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public SquidMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SQUID;
	}
}
