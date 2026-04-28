package org.mockbukkit.mockbukkit.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.MangroveBoat;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link MangroveBoat}.
 *
 * @see BoatMock
 */
public class MangroveBoatMock extends BoatMock
		implements
			MangroveBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.boat.MangroveBoatBaseMock
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
