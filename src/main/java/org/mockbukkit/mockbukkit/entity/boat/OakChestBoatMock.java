package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.OakChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link OakChestBoat}.
 *
 * @see ChestBoatMock
 */
public class OakChestBoatMock extends ChestBoatMock
		implements
			OakChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.DarkOakChestBoatBaseMock
{

	public OakChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.OAK_CHEST_BOAT;
	}
}
