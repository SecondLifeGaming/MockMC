package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.PaleOakBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link PaleOakBoat}.
 *
 * @see BoatMock
 */
public class PaleOakBoatMock extends BoatMock
		implements
			PaleOakBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.PaleOakBoatBaseMock
{

	public PaleOakBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.PALE_OAK_BOAT;
	}
}
