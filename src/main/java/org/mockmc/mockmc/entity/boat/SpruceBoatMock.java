package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.SpruceBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link SpruceBoat}.
 *
 * @see BoatMock
 */
public class SpruceBoatMock extends BoatMock
		implements
			SpruceBoat,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.SpruceBoatBaseMock
{

	public SpruceBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SPRUCE_BOAT;
	}
}
