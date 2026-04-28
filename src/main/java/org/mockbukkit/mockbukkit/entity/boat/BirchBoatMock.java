package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.BirchBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link BirchBoat}.
 *
 * @see BoatMock
 */
public class BirchBoatMock extends BoatMock
		implements
			BirchBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.BirchBoatBaseMock
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
