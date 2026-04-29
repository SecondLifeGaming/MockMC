package org.mockmc.mockmc.entity;

import org.bukkit.entity.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Vehicle}.
 *
 * @see EntityMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public abstract class VehicleMock extends EntityMock
		implements
			Vehicle,
			org.mockmc.mockmc.generated.org.bukkit.entity.VehicleBaseMock
{

	/**
	 * Constructs a new {@link VehicleMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected VehicleMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public String toString()
	{
		return "VehicleMock{passenger=" + getPassenger() + '}';
	}
}
