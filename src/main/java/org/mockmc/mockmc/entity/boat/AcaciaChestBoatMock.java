package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.AcaciaChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link AcaciaChestBoat}.
 *
 * @see ChestBoatMock
 */
public class AcaciaChestBoatMock extends ChestBoatMock
		implements
			AcaciaChestBoat,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.boat.AcaciaChestBoatBaseMock
{

	public AcaciaChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ACACIA_CHEST_BOAT;
	}
}
