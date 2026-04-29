package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.JungleChestBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link JungleChestBoat}.
 *
 * @see ChestBoatMock
 */
public class JungleChestBoatMock extends ChestBoatMock
		implements
			JungleChestBoat,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.JungleChestBoatBaseMock
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
