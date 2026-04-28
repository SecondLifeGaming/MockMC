package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.BirchChestBoat;
import org.bukkit.entity.boat.CherryChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link BirchChestBoat}.
 *
 * @see ChestBoatMock
 */
public class CherryChestBoatMock extends ChestBoatMock
		implements
			CherryChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.CherryChestBoatBaseMock
{

	public CherryChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.CHERRY_CHEST_BOAT;
	}
}
