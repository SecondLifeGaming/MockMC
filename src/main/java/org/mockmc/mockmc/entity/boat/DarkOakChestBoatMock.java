package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.DarkOakChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link DarkOakChestBoat}.
 *
 * @see ChestBoatMock
 */
public class DarkOakChestBoatMock extends ChestBoatMock
		implements
			DarkOakChestBoat,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.DarkOakChestBoatBaseMock
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
