package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.inventory.ChiseledBookshelfInventory;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.inventory.ChiseledBookshelfInventoryMock;

/**
 * Mock implementation of a {@link ChiseledBookshelf}.
 *
 * @see TileStateMock
 */
public class ChiseledBookshelfStateMock extends TileStateMock
		implements
			ChiseledBookshelf,
			org.mockmc.mockmc.generated.server.org.bukkit.block.ChiseledBookshelfBaseMock
{
	private final ChiseledBookshelfInventoryMock inventory = new ChiseledBookshelfInventoryMock(this);

	private int lastInteractedSlot = -1;

	protected ChiseledBookshelfStateMock(@NotNull Block block)
	{
		super(block);
	}

	protected ChiseledBookshelfStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected ChiseledBookshelfStateMock(@NotNull TileStateMock state)
	{
		super(state);
	}

	@Override
	public int getLastInteractedSlot()
	{
		return this.lastInteractedSlot;
	}

	@Override
	public void setLastInteractedSlot(int lastInteractedSlot)
	{
		this.lastInteractedSlot = lastInteractedSlot;
	}

	@Override
	public @NotNull ChiseledBookshelfInventory getInventory()
	{
		return this.inventory;
	}

	@Override
	public @NotNull ChiseledBookshelfInventory getSnapshotInventory()
	{
		return this.inventory.getSnapshot();
	}

	@Override
	public @NotNull ChiseledBookshelfStateMock getSnapshot()
	{
		return new ChiseledBookshelfStateMock(this);
	}

}
