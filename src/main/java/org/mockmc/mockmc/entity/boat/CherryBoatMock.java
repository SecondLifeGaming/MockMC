package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.CherryBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link CherryBoat}.
 *
 * @see BoatMock
 */
public class CherryBoatMock extends BoatMock
		implements
			CherryBoat,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.boat.CherryBoatBaseMock
{

	public CherryBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.CHERRY_BOAT;
	}
}
