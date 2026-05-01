package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.BirchChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link BirchChestBoat}.
 *
 * @see ChestBoatMock
 */
public class BirchChestBoatMock extends ChestBoatMock
		implements
			BirchChestBoat,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.boat.BirchChestBoatBaseMock
{

	public BirchChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.BIRCH_CHEST_BOAT;
	}
}
