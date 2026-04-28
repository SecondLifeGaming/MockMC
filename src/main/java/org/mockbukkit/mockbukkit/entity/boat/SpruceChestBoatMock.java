package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.SpruceChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link SpruceChestBoat}.
 *
 * @see ChestBoatMock
 */
public class SpruceChestBoatMock extends ChestBoatMock
		implements
			SpruceChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.SpruceChestBoatBaseMock
{

	public SpruceChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SPRUCE_CHEST_BOAT;
	}
}
