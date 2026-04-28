package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.OakBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link OakBoat}.
 *
 * @see BoatMock
 */
public class OakBoatMock extends BoatMock
		implements
			OakBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.OakBoatBaseMock
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
