package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.MangroveBoat;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link MangroveBoat}.
 *
 * @see BoatMock
 */
public class MangroveBoatMock extends BoatMock
		implements
			MangroveBoat,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.boat.MangroveBoatBaseMock
{

	public MangroveBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.MANGROVE_BOAT;
	}
}
