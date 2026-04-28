package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.DarkOakBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link DarkOakBoat}.
 *
 * @see BoatMock
 */
public class DarkOakBoatMock extends BoatMock
		implements
			DarkOakBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.DarkOakBoatBaseMock
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
