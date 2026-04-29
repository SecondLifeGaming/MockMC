package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.BirchBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link BirchBoat}.
 *
 * @see BoatMock
 */
public class BirchBoatMock extends BoatMock
		implements
			BirchBoat,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.BirchBoatBaseMock
{

	public BirchBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.BIRCH_BOAT;
	}
}
