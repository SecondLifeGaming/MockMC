package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.JungleBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link JungleBoat}.
 *
 * @see BoatMock
 */
public class JungleBoatMock extends BoatMock
		implements
			JungleBoat,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.boat.JungleBoatBaseMock
{

	public JungleBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.JUNGLE_BOAT;
	}
}
