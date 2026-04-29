package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.DarkOakBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link DarkOakBoat}.
 *
 * @see BoatMock
 */
public class DarkOakBoatMock extends BoatMock
		implements
			DarkOakBoat,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.DarkOakBoatBaseMock
{

	public DarkOakBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.DARK_OAK_BOAT;
	}
}
