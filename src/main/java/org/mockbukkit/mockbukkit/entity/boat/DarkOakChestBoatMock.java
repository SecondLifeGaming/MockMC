package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.DarkOakChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link DarkOakChestBoat}.
 *
 * @see ChestBoatMock
 */
public class DarkOakChestBoatMock extends ChestBoatMock
		implements
			DarkOakChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.DarkOakChestBoatBaseMock
{

	public DarkOakChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.DARK_OAK_CHEST_BOAT;
	}
}
