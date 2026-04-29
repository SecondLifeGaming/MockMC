package org.mockmc.mockmc.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Marker;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Marker}.
 */
public class MarkerMock extends EntityMock
		implements
			Marker,
			org.mockmc.mockmc.generated.org.bukkit.entity.MarkerBaseMock
{

	/**
	 * Constructs a new {@link MarkerMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public MarkerMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.MARKER;
	}

	@Override
	public boolean addPassenger(@NotNull Entity passenger)
	{
		return false;
	}
}
