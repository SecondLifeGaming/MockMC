package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

public class BlockDisplayMock extends DisplayMock
		implements
			BlockDisplay,
			org.mockmc.mockmc.generated.org.bukkit.entity.BlockDisplayBaseMock
{

	private BlockData blockData = Material.AIR.createBlockData();

	/**
	 * Constructs a new EntityMock on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public BlockDisplayMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public BlockData getBlock()
	{
		return blockData.clone();
	}

	@Override
	public void setBlock(@NotNull BlockData block)
	{
		this.blockData = block.clone();
	}

	@Override
	public EntityType getType()
	{
		return EntityType.BLOCK_DISPLAY;
	}
}
