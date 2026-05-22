package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.BambooRaft;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.BoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link BambooRaft}.
 *
 * @see BoatMock
 */
public class BambooRaftMock extends BoatMock
		implements
			BambooRaft,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.boat.BambooRaftBaseMock
{

	public BambooRaftMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.BAMBOO_RAFT;
	}
}
