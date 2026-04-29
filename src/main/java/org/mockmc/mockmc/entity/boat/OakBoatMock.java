package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.OakBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link OakBoat}.
 *
 * @see BoatMock
 */
public class OakBoatMock extends BoatMock
		implements
			OakBoat,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.OakBoatBaseMock
{

	public OakBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.OAK_BOAT;
	}
}
