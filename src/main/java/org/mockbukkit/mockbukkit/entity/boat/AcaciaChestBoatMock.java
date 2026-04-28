package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.AcaciaChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link AcaciaChestBoat}.
 *
 * @see ChestBoatMock
 */
public class AcaciaChestBoatMock extends ChestBoatMock
		implements
			AcaciaChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.AcaciaChestBoatBaseMock
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
