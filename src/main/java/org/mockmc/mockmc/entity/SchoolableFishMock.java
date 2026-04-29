package org.mockmc.mockmc.entity;

import io.papermc.paper.entity.SchoolableFish;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link SchoolableFish}.
 *
 * @see FishMock
 */
public abstract class SchoolableFishMock extends FishMock implements SchoolableFish
{

	/**
	 * Constructs a new {@link SchoolableFishMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected SchoolableFishMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}
}
