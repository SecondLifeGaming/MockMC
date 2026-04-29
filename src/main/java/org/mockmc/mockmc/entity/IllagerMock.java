package org.mockmc.mockmc.entity;

import org.bukkit.entity.Illager;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Illager}.
 *
 * @see RaiderMock
 */
public abstract class IllagerMock extends RaiderMock
		implements
			Illager,
			org.mockmc.mockmc.generated.org.bukkit.entity.IllagerBaseMock
{

	/**
	 * Constructs a new {@link IllagerMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected IllagerMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
