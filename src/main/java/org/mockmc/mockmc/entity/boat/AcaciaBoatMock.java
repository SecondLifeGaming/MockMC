package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.AcaciaBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link AcaciaBoat}.
 *
 * @see BoatMock
 */
public class AcaciaBoatMock extends BoatMock
		implements
			AcaciaBoat,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.boat.AcaciaBoatBaseMock
{

	public AcaciaBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ACACIA_BOAT;
	}
}
