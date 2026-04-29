package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.PaleOakChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link PaleOakChestBoat}.
 *
 * @see ChestBoatMock
 */
public class PaleOakChestBoatMock extends ChestBoatMock
		implements
			PaleOakChestBoat,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.PaleOakChestBoatBaseMock
{

	public PaleOakChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.PALE_OAK_CHEST_BOAT;
	}
}
