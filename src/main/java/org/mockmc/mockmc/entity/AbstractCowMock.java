package org.mockmc.mockmc.entity;

import org.bukkit.entity.AbstractCow;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link AbstractCow}.
 *
 * @see AnimalsMock
 */
public class AbstractCowMock extends AnimalsMock
		implements
			AbstractCow,
			org.mockmc.mockmc.generated.org.bukkit.entity.AbstractCowBaseMock
{

	/**
	 * Constructs a new {@link AbstractCow} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected AbstractCowMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
