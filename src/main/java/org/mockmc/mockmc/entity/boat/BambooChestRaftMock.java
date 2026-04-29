package org.mockmc.mockmc.entity.boat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.boat.BambooChestRaft;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.ChestBoatMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link BambooChestRaft}.
 *
 * @see ChestBoatMock
 */
public class BambooChestRaftMock extends ChestBoatMock
		implements
			BambooChestRaft,
			org.mockmc.mockmc.generated.org.bukkit.entity.boat.BambooChestRaftBaseMock
{

	public BambooChestRaftMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.BAMBOO_CHEST_RAFT;
	}
}
