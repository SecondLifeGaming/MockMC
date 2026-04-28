package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.PaleOakChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link PaleOakChestBoat}.
 *
 * @see ChestBoatMock
 */
public class PaleOakChestBoatMock extends ChestBoatMock
		implements
			PaleOakChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.PaleOakChestBoatBaseMock
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
