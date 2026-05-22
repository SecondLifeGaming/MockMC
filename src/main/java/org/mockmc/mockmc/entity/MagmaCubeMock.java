package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

public class MagmaCubeMock extends SlimeMock
		implements
			MagmaCube,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.MagmaCubeBaseMock
{

	/**
	 * Constructs a new {@link MagmaCubeMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public MagmaCubeMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.MAGMA_CUBE;
	}
}
