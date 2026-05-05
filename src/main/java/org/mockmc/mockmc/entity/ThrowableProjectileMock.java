package org.mockmc.mockmc.entity;

import org.bukkit.entity.ThrowableProjectile;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link ThrowableProjectile}.
 *
 * @see ProjectileMock
 */
public abstract class ThrowableProjectileMock extends ProjectileMock
		implements
			ThrowableProjectile,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.ThrowableProjectileBaseMock
{

	/**
	 * Constructs a new {@link ThrowableProjectileMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected ThrowableProjectileMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
