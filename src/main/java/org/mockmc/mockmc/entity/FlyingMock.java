package org.mockmc.mockmc.entity;

import org.bukkit.entity.Flying;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Flying}.
 *
 * @see MobMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public abstract class FlyingMock extends MobMock
		implements
			Flying,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.FlyingBaseMock
{

	/**
	 * Constructs a new {@link FlyingMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected FlyingMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
