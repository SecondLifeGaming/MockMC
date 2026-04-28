package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.MangroveChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link MangroveChestBoat}.
 *
 * @see ChestBoatMock
 */
public class MangroveChestBoatMock extends ChestBoatMock
		implements
			MangroveChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.MangroveChestBoatBaseMock
{

	public MangroveChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.MANGROVE_CHEST_BOAT;
	}
}
