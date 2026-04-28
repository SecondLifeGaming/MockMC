package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.JungleChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link JungleChestBoat}.
 *
 * @see ChestBoatMock
 */
public class JungleChestBoatMock extends ChestBoatMock
		implements
			JungleChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.JungleChestBoatBaseMock
{

	public JungleChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.JUNGLE_CHEST_BOAT;
	}
}
