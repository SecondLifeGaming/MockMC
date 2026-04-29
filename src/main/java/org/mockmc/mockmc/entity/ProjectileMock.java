package org.mockmc.mockmc.entity;

import org.bukkit.entity.Projectile;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Projectile}.
 *
 * @see AbstractProjectileMock
 */
public abstract class ProjectileMock extends AbstractProjectileMock
{

	/**
	 * Constructs a new {@link ProjectileMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected ProjectileMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
